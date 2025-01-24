package com.example.springbootforyourproperty.yourproperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
public class BusinessPlace extends Property{
    // key features
    @ElementCollection
    @CollectionTable(name = "Property Business Place Purpose", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> purpose;

    private String ventilation;

    @ElementCollection
    @CollectionTable(name = "Property Business Place Power Supply", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> powerSupply;

    // special features
    private String lightingSetup;
    private String waitingArea;
    private String visibilityFromRoad;
    private String insuranceDetails;

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
