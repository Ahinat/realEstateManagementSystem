package com.example.springbootforyourproperty.yourproperty;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
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

    public Apartment(Long aLong, String s, String s1, BigDecimal bigDecimal, BigDecimal bigDecimal1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        super();
    }

    public Apartment() {

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
