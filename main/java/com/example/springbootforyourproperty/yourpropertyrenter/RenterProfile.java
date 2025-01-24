package com.example.springbootforyourproperty.yourpropertyrenter;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
public class RenterProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long renterId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private YourPropertyUser yourPropertyUser;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Links back to the child entity
    private Set<RenterApartmentFeatures> apartmentFeatures;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Links back to the child entity
    private Set<RenterHouseFeatures> houseFeatures;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Links back to the child entity
    private Set<RenterLandFeatures> landFeatures;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Links back to the child entity
    private Set<RenterResidentialUnitFeatures> residentialUnitFeatures;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Links back to the child entity
    private Set<RenterCommercialUnitFeatures> commercialUnitFeatures;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Links back to the child entity
    private Set<RenterBusinessPlaceFeatures> businessPlaceFeatures;

    private BigDecimal minBudget;
    private BigDecimal maxBudget;
    private String moveInDate;
    private String rentalTerm;

    @ElementCollection
    @CollectionTable(name = "renter_preferred_property_types", joinColumns = @JoinColumn(name = "renter_id"))
    @Column(name = "property_type")
    private List<String> preferredPropertyTypes;



    @ElementCollection
    @CollectionTable(name = "renter_preferred_locations", joinColumns = @JoinColumn(name = "renter_id"))
    @Column(name = "location")
    private List<String> preferredLocations;

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }

    public YourPropertyUser getYourPropertyUser() {
        return yourPropertyUser;
    }

    public void setYourPropertyUser(YourPropertyUser yourPropertyUser) {
        this.yourPropertyUser = yourPropertyUser;
    }

    public Set<RenterApartmentFeatures> getApartmentFeatures() {
        return apartmentFeatures;
    }

    public void setApartmentFeatures(Set<RenterApartmentFeatures> apartmentFeatures) {
        this.apartmentFeatures = apartmentFeatures;
    }

    public Set<RenterHouseFeatures> getHouseFeatures() {
        return houseFeatures;
    }

    public void setHouseFeatures(Set<RenterHouseFeatures> houseFeatures) {
        this.houseFeatures = houseFeatures;
    }

    public Set<RenterLandFeatures> getLandFeatures() {
        return landFeatures;
    }

    public void setLandFeatures(Set<RenterLandFeatures> landFeatures) {
        this.landFeatures = landFeatures;
    }

    public Set<RenterResidentialUnitFeatures> getResidentialUnitFeatures() {
        return residentialUnitFeatures;
    }

    public void setResidentialUnitFeatures(Set<RenterResidentialUnitFeatures> residentialUnitFeatures) {
        this.residentialUnitFeatures = residentialUnitFeatures;
    }

    public Set<RenterCommercialUnitFeatures> getCommercialUnitFeatures() {
        return commercialUnitFeatures;
    }

    public void setCommercialUnitFeatures(Set<RenterCommercialUnitFeatures> commercialUnitFeatures) {
        this.commercialUnitFeatures = commercialUnitFeatures;
    }

    public Set<RenterBusinessPlaceFeatures> getBusinessPlaceFeatures() {
        return businessPlaceFeatures;
    }

    public void setBusinessPlaceFeatures(Set<RenterBusinessPlaceFeatures> businessPlaceFeatures) {
        this.businessPlaceFeatures = businessPlaceFeatures;
    }

    public BigDecimal getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(BigDecimal minBudget) {
        this.minBudget = minBudget;
    }

    public BigDecimal getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(BigDecimal maxBudget) {
        this.maxBudget = maxBudget;
    }

    public String getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(String moveInDate) {
        this.moveInDate = moveInDate;
    }

    public String getRentalTerm() {
        return rentalTerm;
    }

    public void setRentalTerm(String rentalTerm) {
        this.rentalTerm = rentalTerm;
    }

    public List<String> getPreferredPropertyTypes() {
        return preferredPropertyTypes;
    }

    public void setPreferredPropertyTypes(List<String> preferredPropertyTypes) {
        this.preferredPropertyTypes = preferredPropertyTypes;
    }

    public List<String> getPreferredLocations() {
        return preferredLocations;
    }

    public void setPreferredLocations(List<String> preferredLocations) {
        this.preferredLocations = preferredLocations;
    }
}
