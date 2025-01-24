package com.example.springbootforyourproperty.yourpropertybuyer;

import com.example.springbootforyourproperty.yourpropertyseller.SellerProfile;
import com.example.springbootforyourproperty.yourpropertyuser.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BuyerProfileService {

    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    @Autowired
    private BuyerProfileRepository buyerProfileRepository;

    @Transactional
    public BuyerProfile createBuyerProfile(String email, BigDecimal minBudget, BigDecimal maxBudget, List<String> propertyTypes, List<String> locations) {

        // Find the user in main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + email + " not found");
        }

        // Check if a BuyerProfile is already associated with this user
        BuyerProfile existingBuyerProfile = buyerProfileRepository.findByYourPropertyUser(yourPropertyUser);
        if (existingBuyerProfile != null) {
            throw new RuntimeException("A BuyerProfile already exists for this user");
        }

        // Create and addProperty a new Buyer instance linked to the user
        BuyerProfile buyer = new BuyerProfile();
        buyer.setYourPropertyUser(yourPropertyUser);
        buyer.setMinBudget(minBudget);
        buyer.setMaxBudget(maxBudget);
        buyer.setPreferredPropertyTypes(propertyTypes);
        buyer.setPreferredLocations(locations);

        return buyerProfileRepository.save(buyer);  // Save and return the Buyer
    }

//    public BuyerProfile getBuyer(Long id) {
//        return buyerProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Buyer not found"));
//    }

    // Method to retrieve full Seller Profile by email
    @Transactional
    public BuyerProfile getBuyerProfileByEmail(String email) {
        // Find the user in the main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + email + " not found");
        }

        BuyerProfile buyer = buyerProfileRepository.findByYourPropertyUser(yourPropertyUser);
        if (buyer == null) {
            return null;
        }
        return buyer;
    }

    // Additional methods for updating buyer profiles can go here
}
