package com.example.realestatemanagementsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CommercialUnit extends Property {
    // key features
    private List<String> purpose;
    private String floorLayout;
    private List<String> accessibilityFeatures;

    // special features
    private String facade;
    private List<String> nearbyAttractions;
    private List<String> fireSafety;
    private String airCondition;

    // Default constructor (required for Jackson)
    public CommercialUnit() {}

    // Parameterized constructor
    public CommercialUnit(String offerType, String propertyType, String propertyTitle, String propertyDescription, BigDecimal salePrice, BigDecimal rentPrice, String rentTerm, String rentAvaiableDate, String country, String state, String city, String postalCode, String street, List<String> picture, LocalDateTime createdAt, LocalDateTime updatedAt, List<String> purpose, String floorLayout, List<String> accessibilityFeatures, String facade, List<String> nearbyAttractions, List<String> fireSafety, String airCondition) {
        super(offerType, propertyType, propertyTitle, propertyDescription, salePrice, rentPrice, rentTerm, rentAvaiableDate, country, state, city, postalCode, street, picture, createdAt, updatedAt);
        this.purpose = purpose;
        this.floorLayout = floorLayout;
        this.accessibilityFeatures = accessibilityFeatures;
        this.facade = facade;
        this.nearbyAttractions = nearbyAttractions;
        this.fireSafety = fireSafety;
        this.airCondition = airCondition;
    }

    // Getters and Setters
    public List<String> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<String> purpose) {
        this.purpose = purpose;
    }

    public String getFloorLayout() {
        return floorLayout;
    }

    public void setFloorLayout(String floorLayout) {
        this.floorLayout = floorLayout;
    }

    public List<String> getAccessibilityFeatures() {
        return accessibilityFeatures;
    }

    public void setAccessibilityFeatures(List<String> accessibilityFeatures) {
        this.accessibilityFeatures = accessibilityFeatures;
    }

    public String getFacade() {
        return facade;
    }

    public void setFacade(String facade) {
        this.facade = facade;
    }

    public List<String> getNearbyAttractions() {
        return nearbyAttractions;
    }

    public void setNearbyAttractions(List<String> nearbyAttractions) {
        this.nearbyAttractions = nearbyAttractions;
    }

    public List<String> getFireSafety() {
        return fireSafety;
    }

    public void setFireSafety(List<String> fireSafety) {
        this.fireSafety = fireSafety;
    }

    public String getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(String airCondition) {
        this.airCondition = airCondition;
    }
}
