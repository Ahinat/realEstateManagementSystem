package com.example.realestatemanagementsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ResidentialUnit extends Property{
    // key features
    private List<String> amenities;
    private String storageType;
    private Boolean noiseInsulation;

    // special features
    private Integer configuration;
    private List<String> sharedFacilities;
    private String orientation;
    private String naturalLight;

    // Default constructor (requires for Jackson)
    public ResidentialUnit() {}

    public ResidentialUnit(String offerType, String propertyType, String propertyTitle, String propertyDescription, BigDecimal salePrice, BigDecimal rentPrice, String rentTerm, String rentAvaiableDate, String country, String state, String city, String postalCode, String street, List<String> picture, LocalDateTime createdAt, LocalDateTime updatedAt, List<String> amenities, String storageType, Boolean noiseInsulation, Integer configuration, List<String> sharedFacilities, String orientation, String naturalLight) {
        super(offerType, propertyType, propertyTitle, propertyDescription, salePrice, rentPrice, rentTerm, rentAvaiableDate, country, state, city, postalCode, street, picture, createdAt, updatedAt);
        this.amenities = amenities;
        this.storageType = storageType;
        this.noiseInsulation = noiseInsulation;
        this.configuration = configuration;
        this.sharedFacilities = sharedFacilities;
        this.orientation = orientation;
        this.naturalLight = naturalLight;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public Boolean getNoiseInsulation() {
        return noiseInsulation;
    }

    public void setNoiseInsulation(Boolean noiseInsulation) {
        this.noiseInsulation = noiseInsulation;
    }

    public Integer getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Integer configuration) {
        this.configuration = configuration;
    }

    public List<String> getSharedFacilities() {
        return sharedFacilities;
    }

    public void setSharedFacilities(List<String> sharedFacilities) {
        this.sharedFacilities = sharedFacilities;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getNaturalLight() {
        return naturalLight;
    }

    public void setNaturalLight(String naturalLight) {
        this.naturalLight = naturalLight;
    }
}
