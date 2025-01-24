package com.example.springbootforyourproperty.yourpropertyrenter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Table(name = "renter_house_features")
public class RenterHouseFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long house_feature_id;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    @JsonBackReference // Prevents infinite recursion
    private RenterProfile renter;

    private String basement;

    @ElementCollection
    @CollectionTable(name = "renter_house_security_features", joinColumns = @JoinColumn(name = "house_feature_id"))
    private List<String> security_features;

    private Boolean garage;

    public Long getHouse_feature_id() {
        return house_feature_id;
    }

    public void setHouse_feature_id(Long house_feature_id) {
        this.house_feature_id = house_feature_id;
    }

    public RenterProfile getRenter() {
        return renter;
    }

    public void setRenter(RenterProfile renter) {
        this.renter = renter;
    }

    public String getBasement() {
        return basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public List<String> getSecurity_features() {
        return security_features;
    }

    public void setSecurity_features(List<String> security_features) {
        this.security_features = security_features;
    }

    public Boolean getGarage() {
        return garage;
    }

    public void setGarage(Boolean garage) {
        this.garage = garage;
    }
}
