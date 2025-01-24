package com.example.springbootforyourproperty.yourpropertyseller;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerProfileService {
    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    @Autowired
    private SellerProfileRepository sellerProfileRepository;

    @Transactional
    public SellerProfile createSellerProfile(JsonNode sellerProfile) {
        String userEmail = sellerProfile.get("email").asText();

        // Find the user in main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(userEmail);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + userEmail + " not found");
        }

        // Check if a SellerProfile is already associated with this user
        SellerProfile existingBuyerProfile = sellerProfileRepository.findByYourPropertyUser(yourPropertyUser);
        if (existingBuyerProfile != null) {
            throw new RuntimeException("A BuyerProfile already exists for this user");
        }

        // Create and addProperty a new seller instance linked to the user
        SellerProfile seller = new SellerProfile();
        seller.setYourPropertyUser(yourPropertyUser);

        String SellerLicense = sellerProfile.get("sellerLicense").asText();
        List<String> propertyTypes = new ArrayList<>();
        for(JsonNode property : sellerProfile.get("propertyTypes")) {
            propertyTypes.add(property.asText());
        }
        List<String> openCommunication = new ArrayList<>();
        for(JsonNode comm : sellerProfile.get("openCommunication")) {
            openCommunication.add(comm.asText());
        }

        seller.setSellerLicense(SellerLicense);
        seller.setSellerOwnedPropertyType(propertyTypes);
        seller.setSellerOpenedCommunictionUserType(openCommunication);

        return sellerProfileRepository.save(seller); // addProperty and return the seller
    }

    // Method to retrieve full Seller Profile by email
    @Transactional
    public SellerProfile getSellerProfileByEmail(String email) {
        // Find the user in the main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + email + " not found");
        }

        SellerProfile seller = sellerProfileRepository.findByYourPropertyUser(yourPropertyUser);
        if (seller == null) {
            return null;
        }
        return seller;
    }
}
