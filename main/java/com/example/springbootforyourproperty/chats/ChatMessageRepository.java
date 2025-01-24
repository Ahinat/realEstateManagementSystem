package com.example.springbootforyourproperty.chats;

import com.example.springbootforyourproperty.yourproperty.Property;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySender(YourPropertyUser sender);
    List<ChatMessage> findByReceiver(YourPropertyUser receiver);
    List<ChatMessage> findByProperty(Property property);
    List<ChatMessage> findBySenderAndReceiverAndProperty(YourPropertyUser sender, YourPropertyUser receiver, Property property);

    @Query("SELECT cm FROM ChatMessage cm WHERE cm.property.propertyId = :propertyId AND ((cm.sender.userId = :senderId AND cm.receiver.userId = :receiverId) OR (cm.sender.userId = :receiverId AND cm.receiver.userId = :senderId)) ORDER BY cm.timestamp ASC")
    List<ChatMessage> findChatsBetweenUsersAndProperty(@Param("propertyId") Long propertyId, @Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

    @Query(value = """

            (
            SELECT
                'CHAT' AS type,
                cm.message_id AS Id,
                cm.message_content AS content,
                cm.timestamp AS createdAt,
                cm.sender_user_id AS senderId,
                cm.receiver_user_id AS receiverId,
                cm.property_id AS propertyId,
                NULL AS status
            FROM chat_message cm
            WHERE cm.property_id = :propertyId
              AND (
                  (cm.sender_user_id = :userId1 AND cm.receiver_user_id = :userId2)
                  OR
                  (cm.sender_user_id = :userId2 AND cm.receiver_user_id = :userId1)
              )
        )
        UNION ALL
        (
            SELECT
                'NEGOTIATION' AS type,
                n.negotiation_id AS Id,
                CASE
        			WHEN n.negotiated_sale_price IS NOT NULL THEN n.negotiated_sale_price
        			WHEN n.negotiated_rent_price IS NOT NULL THEN CONCAT(n.negotiated_rent_price, ' (', n.negotiated_rent_term, ')')
        		END
                AS content,
                n.offered_at AS createdAt,
                n.negotiator_id AS senderId,
                n.recipient_id AS receiverId,
                n.property_id AS propertyId,
                n.status AS status
            FROM negotiation n
            WHERE n.property_id = :propertyId
              AND (
                  (n.negotiator_id = :userId2 AND n.recipient_id = :userId1)
                  OR\s
                  (n.negotiator_id = :userId1 AND n.recipient_id = :userId2)
              )
        )
        ORDER BY createdAt ASC;
        """, nativeQuery = true)
    List<Map<String, Object>> fetchChatAndNegotiationHistory(@Param("propertyId") Long propertyId, @Param("userId1") Long userId1, @Param("userId2") Long userId2);

//    @Query(value = "SELECT " +
//            "CONCAT(u_sender.`first name`, ' ', u_sender.`last name`) AS senderName, " +
//            "u_sender.`e-mail` AS senderEmail, " +
//            "u_sender.profile_picture AS senderProfilePicture, " +
//            "p.`property title` AS propertyTitle, " +
//            "p.`property sale price` AS propertySalePrice, " +
//            "p.`property rent price` AS propertyRentPrice, " +
//            "p.`property rental term` AS propertyRentTerm, " +
//            "CONCAT(p.`property street`, ', ', p.`property city`, ', ', p.`property state`, ', ', p.`property postal code`, ', ', p.`property country`) AS propertyLocation, " +
//            "(SELECT pp.pictures " +
//            " FROM `property pictures` pp " +
//            " WHERE pp.property_id = p.property_id " +
//            " ORDER BY pp.property_id LIMIT 1) AS firstPicture, " +
//            "MAX(cm.timestamp) AS lastTimestamp, " +
//            "SUBSTRING_INDEX(GROUP_CONCAT(cm.message_content ORDER BY cm.timestamp DESC), ',', 1) AS lastMessage " +
//            "FROM chat_message cm " +
//            "JOIN your_property_user u_sender ON cm.sender_user_id = u_sender.user_id " +
//            "JOIN your_property_user u_receiver ON cm.receiver_user_id = u_receiver.user_id " +
//            "JOIN property p ON cm.property_id = p.property_id " +
//            "WHERE u_receiver.`e-mail` = :email " +
//            "GROUP BY cm.sender_user_id, cm.property_id",
//            nativeQuery = true)
//    List<Object[]> getInboxMessages(@Param("email") String email);

    @Query(value = """
        SELECT\s
            CASE\s
                WHEN cm_last.sender_user_id = user_query.user_id THEN\s
                    CONCAT(u_receiver.`first name`, ' ', u_receiver.`last name`)
                ELSE\s
                    CONCAT(u_sender.`first name`, ' ', u_sender.`last name`)
            END AS otherPartyName,
            CASE\s
                WHEN cm_last.sender_user_id = user_query.user_id THEN\s
                    u_receiver.`e-mail`
                ELSE\s
                    u_sender.`e-mail`
            END AS otherPartyEmail,
            CASE\s
                WHEN cm_last.sender_user_id = user_query.user_id THEN\s
                    u_receiver.profile_picture
                ELSE\s
                    u_sender.profile_picture
            END AS otherPartyProfilePicture,
            p.`property title` AS propertyTitle,
            p.`property sale price` AS propertySellingPrice,
            p.`property rent price` AS propertyRentingPrice,
            p.`property rental term` AS propertyRentTerm,
            CONCAT(p.`property street`, ', ', p.`property city`, ', ', p.`property state`, ', ', p.`property postal code`, ', ', p.`property country`) AS propertyLocation,
            (SELECT pp.pictures
             FROM `property pictures` pp\s
             WHERE pp.property_id = p.property_id
             ORDER BY pp.property_id LIMIT 1) AS firstPicture,
            cm_last.timestamp AS lastTimestamp,
            cm_last.message_content AS lastMessage,
            u_sender.`e-mail` AS senderEmail
        FROM\s
            (
                SELECT\s
                    cm.property_id,\s
                    LEAST(cm.sender_user_id, cm.receiver_user_id) AS user1,
                    GREATEST(cm.sender_user_id, cm.receiver_user_id) AS user2,
                    MAX(cm.timestamp) AS last_timestamp
                FROM chat_message cm
                GROUP BY cm.property_id, user1, user2
            ) conversation
        JOIN chat_message cm_last\s
            ON cm_last.property_id = conversation.property_id
            AND LEAST(cm_last.sender_user_id, cm_last.receiver_user_id) = conversation.user1
            AND GREATEST(cm_last.sender_user_id, cm_last.receiver_user_id) = conversation.user2
            AND cm_last.timestamp = conversation.last_timestamp
        JOIN your_property_user u_sender\s
            ON cm_last.sender_user_id = u_sender.user_id
        JOIN your_property_user u_receiver\s
            ON cm_last.receiver_user_id = u_receiver.user_id
        JOIN property p\s
            ON cm_last.property_id = p.property_id
        JOIN your_property_user user_query\s
            ON user_query.`e-mail` = :email
        WHERE\s
            conversation.user1 = user_query.user_id
            OR conversation.user2 = user_query.user_id
        \s""", nativeQuery = true)
    List<Object[]> getInboxMessages(@Param("email") String email);
}