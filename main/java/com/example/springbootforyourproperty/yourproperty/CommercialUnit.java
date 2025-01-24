package com.example.springbootforyourproperty.yourproperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
public class CommercialUnit extends Property{
    // key features
    @ElementCollection
    @CollectionTable(name = "Property Commercial Unit Purpose", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> purpose;

    private String floorLayout;

    @ElementCollection
    @CollectionTable(name = "Property Commercial Accessibility Features", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> accessibilityFeatures;

    // special features
    private String facade;

    @ElementCollection
    @CollectionTable(name = "Property Commercial Nearby Attractions", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> nearbyAttractions;

    @ElementCollection
    @CollectionTable(name = "Property Commercial Fire Safety", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> fireSafety;

    private String airCondition;

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
