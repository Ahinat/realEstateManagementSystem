package com.example.springbootforyourproperty.chats;

import com.example.springbootforyourproperty.yourproperty.PropertyRepository;
import com.example.springbootforyourproperty.yourproperty.Property;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatRepository;

    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public void saveChatMessage(String senderEmail, String receiverEmail, String message, String propertyTitle, String propertyLocation) {
        ChatMessage chatMessage = new ChatMessage();

        YourPropertyUser receiver = yourPropertyUserRepository.findByEmail(receiverEmail);
        chatMessage.setReceiver(receiver);

        YourPropertyUser sender = yourPropertyUserRepository.findByEmail(senderEmail);
        chatMessage.setSender(sender);

        chatMessage.setMessageContent(message);

        Optional<Property> property = propertyRepository.findByTitleAndAddress(propertyTitle, propertyLocation);
        property.ifPresent(chatMessage::setProperty);

        chatMessage.setTimestamp(LocalDateTime.now());

        chatRepository.save(chatMessage);
    }

    public List<Map<String, Object>> getChatMessagesBetweenUsersAboutProperty(String senderEmail, String receiverEmail, String propertyTitle, String propertyLocation) {
        YourPropertyUser receiver = yourPropertyUserRepository.findByEmail(receiverEmail);
        YourPropertyUser sender = yourPropertyUserRepository.findByEmail(senderEmail);
        Optional<Property> property = propertyRepository.findByTitleAndAddress(propertyTitle, propertyLocation);

//        List<ChatMessage> chatMessages = chatRepository.findChatsBetweenUsersAndProperty(property.get().getPropertyId(), sender.getUserId(), receiver.getUserId());
//        return chatMessages;
        return chatRepository.findChatsBetweenUsersAndProperty(property.get().getPropertyId(), sender.getUserId(), receiver.getUserId()).stream().map(ChatMessage -> {
            Map<String, Object> chatMessage = new HashMap<>();
            chatMessage.put("sender", ChatMessage.getSender().getEmail());
            chatMessage.put("receiver", ChatMessage.getReceiver().getEmail());
            chatMessage.put("message", ChatMessage.getMessageContent());
            chatMessage.put("timeStamp", ChatMessage.getTimestamp());
            return chatMessage;
        }).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getChatAndNegotiationHistory(String senderEmail, String receiverEmail, String propertyTitle, String propertyLocation) {
        // Fetch sender, receiver, and property details
        YourPropertyUser receiver = yourPropertyUserRepository.findByEmail(receiverEmail);
        YourPropertyUser sender = yourPropertyUserRepository.findByEmail(senderEmail);
        Optional<Property> property = propertyRepository.findByTitleAndAddress(propertyTitle, propertyLocation);

        // Validate inputs
        if (receiver == null || sender == null || property.isEmpty()) {
            throw new IllegalArgumentException("Invalid sender, receiver, or property details provided.");
        }

        // Fetch chat and negotiation history
        return chatRepository.fetchChatAndNegotiationHistory(property.get().getPropertyId(), sender.getUserId(), receiver.getUserId()
        ).stream().map(entry -> {
            Map<String, Object> result = new HashMap<>();
            String type = (String) entry.get("type");

            Object rawTimeStamp = entry.get("createdAt");
            LocalDateTime timeStamp = convertToLocalDateTime(rawTimeStamp);

            if ("CHAT".equals(type)) {
                result.put("type", "CHAT");
                result.put("chatId", entry.get("Id"));
                if(sender.getUserId().equals(entry.get("senderId"))) {
                    result.put("sender", sender.getEmail());
                    result.put("receiver", receiver.getEmail());
                } else if(receiver.getUserId().equals(entry.get("senderId"))) {
                    result.put("sender", receiver.getEmail());
                    result.put("receiver", sender.getEmail());
                }
                result.put("message", entry.get("content"));
                result.put("timeStamp", timeStamp);

            } else if ("NEGOTIATION".equals(type)) {

                result.put("type", "NEGOTIATION");
                result.put("negotiationId", entry.get("Id"));
                if(sender.getUserId().equals(entry.get("senderId"))) {
                    result.put("negotiator", sender.getEmail());
                    result.put("recipient", receiver.getEmail());
                } else if(receiver.getUserId().equals(entry.get("senderId"))) {
                    result.put("negotiator", receiver.getEmail());
                    result.put("recipient", sender.getEmail());
                }

                // Modify message based on offerType
                String offerType = property.get().getOfferType();
                String content = (String) entry.get("content");

                result.put("propertyOfferType", offerType);
                if ("For sale".equals(offerType)) {
                    // Treat the content as a negotiated sale price
                    result.put("negotiatedSalePrice", new BigDecimal(content));
                } else if ("For rent".equals(offerType)) {
                    // Split the content into rent price and term
                    String[] parts = content.split(" ", 2);
                    result.put("negotiatedRentPrice", new BigDecimal(parts[0]));
                    if (parts.length > 1) {
                        result.put("negotiatedRentTerm", parts[1].replace("(", "").replace(")", ""));
                    }
                }
                result.put("offeredAt", timeStamp);

                result.put("propertyTitle", property.get().getPropertyTitle());
                result.put("propertyLocation", property.get().getStreet() + ", " + property.get().getCity() + ", " + property.get().getState() + ", " + property.get().getPostalCode() + ", " + property.get().getCountry());
                result.put("status", entry.get("status"));
            }

            return result;
        }).collect(Collectors.toList());
    }

    private LocalDateTime convertToLocalDateTime(Object rawValue) {
        if (rawValue instanceof Timestamp) {
            return ((Timestamp) rawValue).toLocalDateTime();
        } else if (rawValue instanceof String) {
            return LocalDateTime.parse((String) rawValue, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } else {
            throw new IllegalArgumentException("Unsupported timestamp type: " + rawValue.getClass());
        }
    }


    public List<Map<String, Object>> getInboxMessagesWithSenderAndProperty(String userEmail) {
        List<Object[]> inboxList = chatRepository.getInboxMessages(userEmail);
        List<Map<String, Object>> inboxMessages = new ArrayList<>();

        for(Object[] inbox : inboxList) {
            Map<String, Object> inboxMessage = new HashMap<>();
            inboxMessage.put("chatWithName", inbox[0]);
            inboxMessage.put("chatWithEmail", inbox[1]);
            inboxMessage.put("chatWithProfilePicture", inbox[2]);
            inboxMessage.put("propertyTitle", inbox[3]);
            if(inbox[4] != null) {
                inboxMessage.put("propertyPrice", inbox[4]);
            } else if(inbox[5] != null && inbox[6] != null) {
                inboxMessage.put("propertyPrice", inbox[5] + " (" + inbox[6] + ")");
            }
            inboxMessage.put("propertyLocation", inbox[7]);
            inboxMessage.put("propertyPicture", inbox[8]);
            inboxMessage.put("recentMessageTimeStamp", inbox[9]);
            inboxMessage.put("recentMessage", inbox[10]);
            inboxMessage.put("senderEmail", inbox[11]);
            inboxMessages.add(inboxMessage);
        }

        return inboxMessages;
    }
}
