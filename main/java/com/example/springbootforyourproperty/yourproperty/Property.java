package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyseller.SellerProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Property")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "seller_id", referencedColumnName = "sellerId", nullable = false)
    private SellerProfile sellerProfile;

    @Column(name = "Property Offer Type")
    @JsonProperty("OfferType")
    private String offerType;

    @Column(name = "Property Type")
    @JsonProperty("PropertyType")
    private String propertyType;

    @Column(name = "Property Title")
    @JsonProperty("PropertyTitle")
    private String propertyTitle;

    @Column(name = "Property Description")
    @JsonProperty("PropertyDescription")
    private String propertyDescription;

    @Column(name = "Property Sale Price")
    @JsonProperty("SalePrice")
    private BigDecimal salePrice;

    @Column(name = "Property Rent Price")
    @JsonProperty("RentPrice")
    private BigDecimal rentPrice;

    @Column(name = "Property Rental Term")
    @JsonProperty("RentTerm")
    private String rentTerm;

    @Column(name = "Property Rent Availability Date")
    @JsonProperty("RentAvailableDate")
    private String rentAvailableDate;

    @Column(name = "Property Country")
    @JsonProperty("Country")
    private String country;

    @Column(name = "Property State")
    @JsonProperty("State")
    private String state;

    @Column(name = "Property City")
    @JsonProperty("City")
    private String city;

    @Column(name = "Property Postal Code")
    @JsonProperty("PostCode")
    private String postalCode;

    @Column(name = "Property Street")
    @JsonProperty("Street")
    private String street;

    @ElementCollection
    @CollectionTable(name = "Property Pictures", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "Pictures")
    private List<String> picture;

//    @Column(nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(nullable = false)
//    private LocalDateTime updatedAt;
//
//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public SellerProfile getSellerProfile() {
        return sellerProfile;
    }

    public void setSellerProfile(SellerProfile sellerProfile) {
        this.sellerProfile = sellerProfile;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentTerm() {
        return rentTerm;
    }

    public void setRentTerm(String rentTerm) {
        this.rentTerm = rentTerm;
    }

    public String getRentAvailableDate() {
        return rentAvailableDate;
    }

    public void setRentAvailableDate(String rentAvailableDate) {
        this.rentAvailableDate = rentAvailableDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

//    public LocalDateTime getOfferedAt() {
//        return createdAt;
//    }
//
//    public void setOfferedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
}
