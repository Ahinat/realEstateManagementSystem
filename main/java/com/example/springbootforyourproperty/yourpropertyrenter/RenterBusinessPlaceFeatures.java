package com.example.springbootforyourproperty.yourpropertyrenter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "renter_business_place_features")
public class RenterBusinessPlaceFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long business_place_features_id;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    @JsonBackReference // Prevents infinite recursion
    private RenterProfile renter;

    @ElementCollection
    @CollectionTable(name = "renter_business_place_purpose", joinColumns = @JoinColumn(name = "business_place_features_id"))
    private List<String> purpose;

    private String ventilation;

    @ElementCollection
    @CollectionTable(name = "renter_business_place_power_supply", joinColumns = @JoinColumn(name = "business_place_features_id"))
    private List<String> powerSupply;

    public Long getBusiness_place_features_id() {
        return business_place_features_id;
    }

    public void setBusiness_place_features_id(Long business_place_features_id) {
        this.business_place_features_id = business_place_features_id;
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
}
