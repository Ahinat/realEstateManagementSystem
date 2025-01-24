package com.example.springbootforyourproperty.chats;

import com.example.springbootforyourproperty.yourproperty.Property;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NegotiationRepository extends JpaRepository<Negotiation, Long> {

    List<Negotiation> findByNegotiatorAndProperty(YourPropertyUser negotiator, Property property);
    List<Negotiation> findByRecipientAndProperty(YourPropertyUser seller, Property property);

    @Transactional
    @Modifying
    @Query("UPDATE Negotiation n SET n.status = :status WHERE n.negotiationId = :negotiationId")
    void updateNegotiationStatus(@Param("negotiationId") Long negotiationId, @Param("status") Negotiation.Status status);

    @Query("SELECT n FROM Negotiation n WHERE n.negotiator.userId = :userId AND n.property.propertyId = :propertyId")
    List<Negotiation> findNegotiationsByNegotiatorAndProperty(@Param("userId") Long userId, @Param("propertyId") Long propertyId);

    @Query("SELECT n FROM Negotiation n WHERE n.property.propertyId = :propertyId AND " +
            "(n.negotiator.email = :negotiator OR n.recipient.email = :negotiator) AND " +
            "(n.negotiator.email = :recipient OR n.recipient.email = :recipient)")
    List<Negotiation> findByPropertyAndUsers(@Param("propertyId") Long propertyId,
                                             @Param("negotiator") String negotiator,
                                             @Param("recipient") String recipient);
}