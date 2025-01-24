package com.example.springbootforyourproperty.chats;

import com.example.springbootforyourproperty.yourproperty.Property;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "negotiation")
public class Negotiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long negotiationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negotiator_id", nullable = false)
    private YourPropertyUser negotiator; // Foreign key for buyer/renter

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private YourPropertyUser recipient; // Foreign key for seller

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property; // Foreign key for property

    private BigDecimal negotiatedSalePrice;
    private BigDecimal negotiatedRentPrice;
    private String negotiatedRentTerm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime offeredAt;

    public enum Status {
        OFFERED, ACCEPTED, CANCELLED
    }

    // Getters and setters

    public Long getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Long negotiationId) {
        this.negotiationId = negotiationId;
    }

    public YourPropertyUser getNegotiator() {
        return negotiator;
    }

    public void setNegotiator(YourPropertyUser negotiator) {
        this.negotiator = negotiator;
    }

    public YourPropertyUser getRecipient() {
        return recipient;
    }

    public void setRecipient(YourPropertyUser seller) {
        this.recipient = seller;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public BigDecimal getNegotiatedSalePrice() {
        return negotiatedSalePrice;
    }

    public void setNegotiatedSalePrice(BigDecimal negotiatedSalePrice) {
        this.negotiatedSalePrice = negotiatedSalePrice;
    }

    public BigDecimal getNegotiatedRentPrice() {
        return negotiatedRentPrice;
    }

    public void setNegotiatedRentPrice(BigDecimal negotiatedRentPrice) {
        this.negotiatedRentPrice = negotiatedRentPrice;
    }

    public String getNegotiatedRentTerm() {
        return negotiatedRentTerm;
    }

    public void setNegotiatedRentTerm(String negotiatedRentTerm) {
        this.negotiatedRentTerm = negotiatedRentTerm;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getOfferedAt() {
        return offeredAt;
    }

    public void setOfferedAt(LocalDateTime offeredAt) {
        this.offeredAt = offeredAt;
    }
}
