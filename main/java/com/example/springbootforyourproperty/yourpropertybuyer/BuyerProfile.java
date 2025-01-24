package com.example.springbootforyourproperty.yourpropertybuyer;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BuyerProfile{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private YourPropertyUser yourPropertyUser;

    private BigDecimal minBudget;
    private BigDecimal maxBudget;

    @ElementCollection
    @CollectionTable(name = "buyer_preferred_property_types", joinColumns = @JoinColumn(name = "buyer_id"))
    @Column(name = "property_type")
    private List<String> preferredPropertyTypes;

    @ElementCollection
    @CollectionTable(name = "buyer_preferred_locations", joinColumns = @JoinColumn(name = "buyer_id"))
    @Column(name = "location")
    private List<String> preferredLocations;


    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public YourPropertyUser getYourPropertyUser() {
        return yourPropertyUser;
    }

    public void setYourPropertyUser(YourPropertyUser yourPropertyUser) {
        this.yourPropertyUser = yourPropertyUser;
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
