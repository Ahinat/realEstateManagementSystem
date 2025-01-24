package com.example.realestatemanagementsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class House extends Property {
    // key features
    private String basementAvailability;
    private List<String> securityFeatures;
    private Boolean garageAvailability;

    // special features
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer floor;
    private String roofType;
    private Boolean gardenAvailability;
    private Boolean balconyAvailability;

    // Default Constructor (required for Jackson)
    public House() {}

    public House(String offerType, String propertyType, String propertyTitle, String propertyDescription, BigDecimal salePrice, BigDecimal rentPrice, String rentTerm, String rentAvaiableDate, String country, String state, String city, String postalCode, String street, List<String> picture, LocalDateTime createdAt, LocalDateTime updatedAt, String basementAvailability, List<String> securityFeatures, Boolean garageAvailability, Integer bedrooms, Integer bathrooms, Integer floor, String roofType, Boolean gardenAvailability, Boolean balconyAvailability) {
        super(offerType, propertyType, propertyTitle, propertyDescription, salePrice, rentPrice, rentTerm, rentAvaiableDate, country, state, city, postalCode, street, picture, createdAt, updatedAt);
        this.basementAvailability = basementAvailability;
        this.securityFeatures = securityFeatures;
        this.garageAvailability = garageAvailability;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.floor = floor;
        this.roofType = roofType;
        this.gardenAvailability = gardenAvailability;
        this.balconyAvailability = balconyAvailability;
    }

    public String getBasementAvailability() {
        return basementAvailability;
    }

    public void setBasementAvailability(String basementAvailability) {
        this.basementAvailability = basementAvailability;
    }

    public List<String> getSecurityFeatures() {
        return securityFeatures;
    }

    public void setSecurityFeatures(List<String> securityFeatures) {
        this.securityFeatures = securityFeatures;
    }

    public Boolean getGarageAvailability() {
        return garageAvailability;
    }

    public void setGarageAvailability(Boolean garageAvailability) {
        this.garageAvailability = garageAvailability;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public Boolean getGardenAvailability() {
        return gardenAvailability;
    }

    public void setGardenAvailability(Boolean gardenAvailability) {
        this.gardenAvailability = gardenAvailability;
    }

    public Boolean getBalconyAvailability() {
        return balconyAvailability;
    }

    public void setBalconyAvailability(Boolean balconyAvailability) {
        this.balconyAvailability = balconyAvailability;
    }
}
