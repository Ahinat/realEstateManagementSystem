package com.example.springbootforyourproperty.yourproperty;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class House extends Property{
    // key features
    private String basementAvailability;

    @ElementCollection
    @CollectionTable(name = "Property House Security Features", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> securityFeatures;

    private Boolean garageAvailability;

    // special features
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer floor;
    private String roofType;
    private Boolean gardenAvailability;
    private Boolean balconyAvailability;

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

    public Boolean isGardenAvailability() {
        return gardenAvailability;
    }

    public void setGardenAvailability(Boolean gardenAvailability) {
        this.gardenAvailability = gardenAvailability;
    }

    public Boolean isBalconyAvailability() {
        return balconyAvailability;
    }

    public void setBalconyAvailability(Boolean balconyAvailability) {
        this.balconyAvailability = balconyAvailability;
    }
}
