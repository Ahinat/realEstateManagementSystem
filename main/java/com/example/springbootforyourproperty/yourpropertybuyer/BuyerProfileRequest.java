package com.example.springbootforyourproperty.yourpropertybuyer;

import java.math.BigDecimal;
import java.util.List;

public class BuyerProfileRequest {
    private String email;
    private BigDecimal minBudget;
    private BigDecimal maxBudget;
    private List<String> propertyTypes;
    private List<String> locations;

    // Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(BigDecimal minBudget) {
        this.minBudget = minBudget;
    }

    public BigDecimal getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(BigDecimal maxBudget) {
        this.maxBudget = maxBudget;
    }

    public List<String> getPropertyTypes() {
        return propertyTypes;
    }

    public void setPropertyTypes(List<String> propertyTypes) {
        this.propertyTypes = propertyTypes;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
