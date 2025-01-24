package com.example.realestatemanagementsystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BusinessPlace extends Property {
    // key features
    private List<String> purpose;
    private String ventilation;
    private List<String> powerSupply;

    // special features
    private String lightingSetup;
    private String waitingArea;
    private String visibilityFromRoad;
    private String insuranceDetails;

    // Default constructor (required for Jackson)
    public BusinessPlace() {}

    // Parameterized constructor
    public BusinessPlace(String offerType, String propertyType, String propertyTitle, String propertyDescription, BigDecimal salePrice, BigDecimal rentPrice, String rentTerm, String rentAvaiableDate, String country, String state, String city, String postalCode, String street, List<String> picture, LocalDateTime createdAt, LocalDateTime updatedAt, List<String> purpose, String ventilation, List<String> powerSupply, String lightingSetup, String waitingArea, String visibilityFromRoad, String insuranceDetails) {
        super(offerType, propertyType, propertyTitle, propertyDescription, salePrice, rentPrice, rentTerm, rentAvaiableDate, country, state, city, postalCode, street, picture, createdAt, updatedAt);
        this.purpose = purpose;
        this.ventilation = ventilation;
        this.powerSupply = powerSupply;
        this.lightingSetup = lightingSetup;
        this.waitingArea = waitingArea;
        this.visibilityFromRoad = visibilityFromRoad;
        this.insuranceDetails = insuranceDetails;
    }

    // getters and setters
    public List<String> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<String> purpose) {
        this.purpose = purpose;
    }

    public String getVentilation() {
        return ventilation;
    }

    public void setVentilation(String ventilation) {
        this.ventilation = ventilation;
    }

    public List<String> getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(List<String> powerSupply) {
        this.powerSupply = powerSupply;
    }

    public String getLightingSetup() {
        return lightingSetup;
    }

    public void setLightingSetup(String lightingSetup) {
        this.lightingSetup = lightingSetup;
    }

    public String getWaitingArea() {
        return waitingArea;
    }

    public void setWaitingArea(String waitingArea) {
        this.waitingArea = waitingArea;
    }

    public String getVisibilityFromRoad() {
        return visibilityFromRoad;
    }

    public void setVisibilityFromRoad(String visibilityFromRoad) {
        this.visibilityFromRoad = visibilityFromRoad;
    }

    public String getInsuranceDetails() {
        return insuranceDetails;
    }

    public void setInsuranceDetails(String insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }
}
