package com.example.realestatemanagementsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Apartment extends Property{
    // key features
    private Boolean petPolicy;
    private Boolean furniturePolicy;
    private Boolean terracePolicy;
    private Boolean parkingPolicy;

    // special features
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer floor;
    private Integer totalFloor;
    private String maintenanceServices;
    private Boolean balconyPolicy;

    // Default Constructor (required for Jackson)
    public Apartment() {}

    public Apartment(String offerType, String propertyType, String propertyTitle, String propertyDescription, BigDecimal salePrice, BigDecimal rentPrice, String rentTerm, String rentAvaiableDate, String country, String state, String city, String postalCode, String street, List<String> picture, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean petPolicy, Boolean furniturePolicy, Boolean terracePolicy, Boolean parkingPolicy, Integer bedrooms, Integer bathrooms, Integer floor, Integer totalFloor, String maintenanceServices, Boolean balconyPolicy) {
        super(offerType, propertyType, propertyTitle, propertyDescription, salePrice, rentPrice, rentTerm, rentAvaiableDate, country, state, city, postalCode, street, picture, createdAt, updatedAt);
        this.petPolicy = petPolicy;
        this.furniturePolicy = furniturePolicy;
        this.terracePolicy = terracePolicy;
        this.parkingPolicy = parkingPolicy;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.floor = floor;
        this.totalFloor = totalFloor;
        this.maintenanceServices = maintenanceServices;
        this.balconyPolicy = balconyPolicy;
    }

    public Boolean getPetPolicy() {
        return petPolicy;
    }

    public void setPetPolicy(Boolean petPolicy) {
        this.petPolicy = petPolicy;
    }

    public Boolean getFurniturePolicy() {
        return furniturePolicy;
    }

    public void setFurniturePolicy(Boolean furniturePolicy) {
        this.furniturePolicy = furniturePolicy;
    }

    public Boolean getTerracePolicy() {
        return terracePolicy;
    }

    public void setTerracePolicy(Boolean terracePolicy) {
        this.terracePolicy = terracePolicy;
    }

    public Boolean getParkingPolicy() {
        return parkingPolicy;
    }

    public void setParkingPolicy(Boolean parkingPolicy) {
        this.parkingPolicy = parkingPolicy;
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

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getMaintenanceServices() {
        return maintenanceServices;
    }

    public void setMaintenanceServices(String maintenanceServices) {
        this.maintenanceServices = maintenanceServices;
    }

    public Boolean getBalconyPolicy() {
        return balconyPolicy;
    }

    public void setBalconyPolicy(Boolean balconyPolicy) {
        this.balconyPolicy = balconyPolicy;
    }
}
