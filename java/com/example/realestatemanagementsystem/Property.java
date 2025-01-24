package com.example.realestatemanagementsystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property= "PropertyType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Apartment.class, name = "Apartment"),
        @JsonSubTypes.Type(value = House.class, name = "House"),
        @JsonSubTypes.Type(value = Land.class, name = "Land"),
        @JsonSubTypes.Type(value = ResidentialUnit.class, name = "ResidentialUnit"),
        @JsonSubTypes.Type(value = CommercialUnit.class, name = "CommercialUnit"),
        @JsonSubTypes.Type(value = BusinessPlace.class, name = "BusinessPlace")
})
public class Property {
    private Long propertyId;

    @JsonProperty("OfferType")
    private String offerType;

    @JsonProperty("PropertyType")
    private String propertyType;

    @JsonProperty("PropertyTitle")
    private String propertyTitle;

    @JsonProperty("PropertyDescription")
    private String propertyDescription;

    @JsonProperty("SalePrice")
    private BigDecimal salePrice;

    @JsonProperty("RentPrice")
    private BigDecimal rentPrice;

    @JsonProperty("RentTerm")
    private String rentTerm;

    @JsonProperty("RentAvailableDate")
    private String rentAvailableDate;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("State")
    private String state;

    @JsonProperty("City")
    private String city;

    @JsonProperty("PostCode")
    private String postalCode;

    @JsonProperty("Street")
    private String street;

    @JsonProperty("picture")
    private List<String> picture;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

    // Default Constructor (required for Jackson)
    public Property() {}

    public Property(String offerType, String propertyType, String propertyTitle, String propertyDescription, BigDecimal salePrice, BigDecimal rentPrice, String rentTerm, String rentAvailableDate, String country, String state, String city, String postalCode, String street, List<String> picture, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.offerType = offerType;
        this.setPropertyType(propertyType);
        this.propertyTitle = propertyTitle;
        this.propertyDescription = propertyDescription;
        this.salePrice = salePrice;
        this.rentPrice = rentPrice;
        this.rentTerm = rentTerm;
        this.rentAvailableDate = rentAvailableDate;
        this.country = country;
        this.state = state;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.picture = picture;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
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

//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
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

