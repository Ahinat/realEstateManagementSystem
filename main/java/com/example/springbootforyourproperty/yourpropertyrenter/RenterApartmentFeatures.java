package com.example.springbootforyourproperty.yourpropertyrenter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "renter_apartment_features")
public class RenterApartmentFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartment_features_id;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    @JsonBackReference // Prevents infinite recursion
    private RenterProfile renter;

    private Boolean petPolicy;
    private Boolean furnished;
    private Boolean terrace;
    private Boolean parking;

    public Long getApartment_features_id() {
        return apartment_features_id;
    }

    public void setApartment_features_id(Long apartment_features_id) {
        this.apartment_features_id = apartment_features_id;
    }

    public RenterProfile getRenter() {
        return renter;
    }

    public void setRenter(RenterProfile renter) {
        this.renter = renter;
    }

    public Boolean getPetPolicy() {
        return petPolicy;
    }

    public void setPetPolicy(Boolean petPolicy) {
        this.petPolicy = petPolicy;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Boolean getTerraceAvailability() {
        return terrace;
    }

    public void setTerraceAvailability(Boolean terrace) {
        this.terrace = terrace;
    }

    public Boolean getParkingAvailability() {
        return parking;
    }

    public void setParkingAvailability(Boolean parking) {
        this.parking = parking;
    }
}
