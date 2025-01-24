package com.example.springbootforyourproperty.yourpropertyrenter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "renter_residential_unit_features")
public class RenterResidentialUnitFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residential_unit_features_id;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    @JsonBackReference // Prevents infinite recursion
    private RenterProfile renter;

    @ElementCollection
    @CollectionTable(name = "renter_residential_unit_amenities", joinColumns = @JoinColumn(name = "residential_unit_features_id"))
    private List<String> amenities;

    private String storageSpace;
    private Boolean noiseInsulation;

    public Long getResidential_unit_features_id() {
        return residential_unit_features_id;
    }

    public void setResidential_unit_features_id(Long residential_unit_features_id) {
        this.residential_unit_features_id = residential_unit_features_id;
    }

    public RenterProfile getRenter() {
        return renter;
    }

    public void setRenter(RenterProfile renter) {
        this.renter = renter;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(String storageSpace) {
        this.storageSpace = storageSpace;
    }

    public Boolean getNoiseInsulation() {
        return noiseInsulation;
    }

    public void setNoiseInsulation(Boolean noiseInsulation) {
        this.noiseInsulation = noiseInsulation;
    }
}
