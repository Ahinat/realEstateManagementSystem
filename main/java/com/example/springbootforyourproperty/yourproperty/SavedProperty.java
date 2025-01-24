package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class SavedProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savedPropertyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private YourPropertyUser yourPropertyUser;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public Long getSavedPropertyId() {
        return savedPropertyId;
    }

    public void setSavedPropertyId(Long savedPropertyId) {
        this.savedPropertyId = savedPropertyId;
    }

    public YourPropertyUser getUser() {
        return yourPropertyUser;
    }

    public void setUser(YourPropertyUser yourPropertyUser) {
        this.yourPropertyUser = yourPropertyUser;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}