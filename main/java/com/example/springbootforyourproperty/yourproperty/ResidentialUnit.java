package com.example.springbootforyourproperty.yourproperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
public class ResidentialUnit extends Property{
    // key features
    @ElementCollection
    @CollectionTable(name = "Property Residential Unit Amenities", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> amenities;

    private String storageType;
    private Boolean noiseInsulation;

    // special features
    private Integer configuration;

    @ElementCollection
    @CollectionTable(name = "Property Residential Unit Shared Facilities", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> sharedFacilities;

    private String orientation;
    private String naturalLight;

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
