package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import jakarta.el.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SavedPropertyService {
    @Autowired
    private SavedPropertyRepository savedPropertyRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    public SavedProperty saveProperty(String email, String title, String address) {
        YourPropertyUser user = yourPropertyUserRepository.findByEmail(email);
        Optional<Property> property = propertyRepository.findByTitleAndAddress(title, address);

        if (property.isPresent()) {
            SavedProperty savedProperty = new SavedProperty();
            savedProperty.setUser(user);
            savedProperty.setProperty(property.get());

            return savedPropertyRepository.save(savedProperty);
        } else {
            throw new RuntimeException("Property not found");
        }
    }

//    public SavedProperty saveProperty(String email, String title, String address) {
//        YourPropertyUser user = yourPropertyUserRepository.findByEmail(email);
//        Optional<Property> property = propertyRepository.findByEmailAndTitleAndAddress(email, title, address);
//
//        if (property.isPresent()) {
//            SavedProperty savedProperty = new SavedProperty();
//            savedProperty.setUser(user);
//            savedProperty.setProperty(property.get());
//
//            return savedPropertyRepository.save(savedProperty);
//        } else {
//            throw new RuntimeException("Property not found");
//        }
//    }

    // public List<SavedProperty> getSavedProperties(String email) {
    //     YourPropertyUser user = yourPropertyUserRepository.findByEmail(email);
    //     return savedPropertyRepository.findByYourPropertyUser(user);
    // }

    public List<Map<String, Object>> getSavedProperties(String email) {
        YourPropertyUser user = yourPropertyUserRepository.findByEmail(email);
        // List<SavedProperty> savedProperties = savedPropertyRepository.findByYourPropertyUser(user);

        return savedPropertyRepository.findByYourPropertyUser(user).stream().map(savedProperty -> {
            Map<String, Object> propertyMap = new HashMap<>();
            propertyMap.put("PropertyTitle", savedProperty.getProperty().getPropertyTitle());
            propertyMap.put("PropertyLocation", savedProperty.getProperty().getStreet() + ", " + savedProperty.getProperty().getCity() + ", " + savedProperty.getProperty().getState() + ", " + savedProperty.getProperty().getPostalCode() + ", " + savedProperty.getProperty().getCountry());
            propertyMap.put("OfferType", savedProperty.getProperty().getOfferType());
            propertyMap.put("PropertyType", savedProperty.getProperty().getPropertyType());
            propertyMap.put("PropertyPrice", savedProperty.getProperty().getOfferType().equals("For sale") ? savedProperty.getProperty().getSalePrice() : savedProperty.getProperty().getRentPrice() + " (" + savedProperty.getProperty().getRentTerm() + ")");
            propertyMap.put("PropertyDescription", savedProperty.getProperty().getPropertyDescription());
            propertyMap.put("picture", savedProperty.getProperty().getPicture());
            propertyMap.put("isOwner", savedProperty.getProperty().getSellerProfile().getYourPropertyUser().getEmail().equals(email));
            return propertyMap;
        }).collect(Collectors.toList());
    }

    public boolean deleteSavedProperty(String email, String title, String address) {
        YourPropertyUser user = yourPropertyUserRepository.findByEmail(email);
        Optional<Property> property = propertyRepository.findByEmailAndTitleAndAddress(email, title, address);

        if (property.isPresent()) {
            Optional<SavedProperty> savedProperty = savedPropertyRepository.findByYourPropertyUserAndProperty(user, property.get());

            if (savedProperty.isPresent()) {
                savedPropertyRepository.delete(savedProperty.get());
                return true;
            } else {
                throw new PropertyNotFoundException("Property not found in saved properties");
            }
        } else {
            throw new PropertyNotFoundException("Property not found");
        }
    }
}
