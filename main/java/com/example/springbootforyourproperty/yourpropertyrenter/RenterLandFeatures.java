package com.example.springbootforyourproperty.yourpropertyrenter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "renter_land_features")
public class RenterLandFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long land_feature_id;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    @JsonBackReference // Prevents infinite recursion
    private RenterProfile renter;

    private String topography;

    @ElementCollection
    @CollectionTable(name = "renter_land_utilites", joinColumns = @JoinColumn(name = "land_feature_id"))
    private List<String> utilites;

    private String roadAccess;

    public Long getLand_feature_id() {
        return land_feature_id;
    }

    public void setLand_feature_id(Long land_feature_id) {
        this.land_feature_id = land_feature_id;
    }

    public RenterProfile getRenter() {
        return renter;
    }

    public void setRenter(RenterProfile renter) {
        this.renter = renter;
    }

    public String getTopography() {
        return topography;
    }

    public void setTopography(String topography) {
        this.topography = topography;
    }

    public List<String> getUtilites() {
        return utilites;
    }

    public void setUtilites(List<String> utilites) {
        this.utilites = utilites;
    }

    public String getRoadAccess() {
        return roadAccess;
    }

    public void setRoadAccess(String roadAccess) {
        this.roadAccess = roadAccess;
    }
}
