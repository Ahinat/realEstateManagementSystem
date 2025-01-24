package com.example.realestatemanagementsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Land extends Property{
    // key features
    private String topography;
    private List<String> utilities;
    private String roadAccess;

    // special features
    private String type;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal area;
    private String border;
    private boolean previousDevelopment;
    private List<String> nearbyInfrastructure;

    // Default constructor (requires for Jackson)
    public Land() {}

    public Land(String offerType, String propertyType, String propertyTitle, String propertyDescription, BigDecimal salePrice, BigDecimal rentPrice, String rentTerm, String rentAvaiableDate, String country, String state, String city, String postalCode, String street, List<String> picture, LocalDateTime createdAt, LocalDateTime updatedAt, String topography, List<String> utilities, String roadAccess, String type, BigDecimal length, BigDecimal width, BigDecimal area, String border, boolean previousDevelopment, List<String> nearbyInfrastructure) {
        super(offerType, propertyType, propertyTitle, propertyDescription, salePrice, rentPrice, rentTerm, rentAvaiableDate, country, state, city, postalCode, street, picture, createdAt, updatedAt);
        this.topography = topography;
        this.utilities = utilities;
        this.roadAccess = roadAccess;
        this.type = type;
        this.length = length;
        this.width = width;
        this.area = area;
        this.border = border;
        this.previousDevelopment = previousDevelopment;
        this.nearbyInfrastructure = nearbyInfrastructure;
    }

    public String getTopography() {
        return topography;
    }

    public void setTopography(String topography) {
        this.topography = topography;
    }

    public List<String> getUtilities() {
        return utilities;
    }

    public void setUtilities(List<String> utilities) {
        this.utilities = utilities;
    }

    public String getRoadAccess() {
        return roadAccess;
    }

    public void setRoadAccess(String roadAccess) {
        this.roadAccess = roadAccess;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public boolean getPreviousDevelopment() {
        return previousDevelopment;
    }

    public void setPreviousDevelopment(boolean previousDevelopment) {
        this.previousDevelopment = previousDevelopment;
    }

    public List<String> getNearbyInfrastructure() {
        return nearbyInfrastructure;
    }

    public void setNearbyInfrastructure(List<String> nearbyInfrastructure) {
        this.nearbyInfrastructure = nearbyInfrastructure;
    }
}
