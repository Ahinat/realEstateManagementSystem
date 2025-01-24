package com.example.springbootforyourproperty.yourproperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Land extends Property {
    // key features
    private String topography;

    @ElementCollection
    @CollectionTable(name = "Property Land Utilities", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> utilities;

    private String roadAccess;

    // special features
    private String type;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal area;
    private String border;
    private boolean previousDevelopment;

    @ElementCollection
    @CollectionTable(name = "Property Land Nearby Infrastructure", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> nearbyInfrastructure;

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

    public boolean isPreviousDevelopment() {
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
