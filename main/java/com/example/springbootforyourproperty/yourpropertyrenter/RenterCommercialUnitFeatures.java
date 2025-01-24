package com.example.springbootforyourproperty.yourpropertyrenter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "renter_commercial_unit_features")
public class RenterCommercialUnitFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commercial_unit_feature_id;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    @JsonBackReference // Prevents infinite recursion
    private RenterProfile renter;

    @ElementCollection
    @CollectionTable(name = "renter_commercial_unit_purpose", joinColumns = @JoinColumn(name = "commercial_unit_feature_id"))
    private List<String> purpose;

    private String floorLayout;

    @ElementCollection
    @CollectionTable(name = "renter_commercial_unit_accessbility", joinColumns = @JoinColumn(name = "commercial_unit_feature_id"))
    private List<String> accessibility;

    public Long getCommercial_unit_feature_id() {
        return commercial_unit_feature_id;
    }

    public void setCommercial_unit_feature_id(Long commercial_unit_feature_id) {
        this.commercial_unit_feature_id = commercial_unit_feature_id;
    }

    public RenterProfile getRenter() {
        return renter;
    }

    public void setRenter(RenterProfile renter) {
        this.renter = renter;
    }

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

    public List<String> getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(List<String> accessibility) {
        this.accessibility = accessibility;
    }
}
