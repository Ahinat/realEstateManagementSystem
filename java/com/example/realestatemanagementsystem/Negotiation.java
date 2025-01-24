package com.example.realestatemanagementsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Negotiation {
    private String type;
    private Long negotiationId;
    private String negotiator;
    private String recipient;
    private String propertyOfferType;
    private BigDecimal negotiatedSalePrice;
    private BigDecimal negotiatedRentPrice;
    private String negotiatedRentTerm;
    private LocalDateTime offeredAt;
    private String propertyTitle;
    private String propertyLocation;
    private Status status;

    public enum Status {
        OFFERED, ACCEPTED, CANCELLED
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Long negotiationId) {
        this.negotiationId = negotiationId;
    }

    public String getNegotiator() {
        return negotiator;
    }

    public void setNegotiator(String negotiator) {
        this.negotiator = negotiator;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPropertyOfferType() {
        return propertyOfferType;
    }

    public void setPropertyOfferType(String propertyOfferType) {
        this.propertyOfferType = propertyOfferType;
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

    public LocalDateTime getOfferedAt() {
        return offeredAt;
    }

    public void setOfferedAt(LocalDateTime offeredAt) {
        this.offeredAt = offeredAt;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
