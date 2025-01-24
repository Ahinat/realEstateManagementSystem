package com.example.springbootforyourproperty.chats;

import com.example.springbootforyourproperty.yourproperty.Property;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String messageContent;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private YourPropertyUser sender;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private YourPropertyUser receiver;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public YourPropertyUser getReceiver() {
        return receiver;
    }

    public void setReceiver(YourPropertyUser receiver) {
        this.receiver = receiver;
    }

    public YourPropertyUser getSender() {
        return sender;
    }

    public void setSender(YourPropertyUser sender) {
        this.sender = sender;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
