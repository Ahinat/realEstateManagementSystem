package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.chats.Negotiation;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class PurchaseConfirmationService {
    @Autowired
    private YourPropertyUserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PurchaseConfirmationRepository purchaseConfirmationRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Transactional
    public void updateConfirmation(String confirmationJson) {
        try {
            // Parse the JSON
            Map<String, Object> confimationMap = mapper.readValue(confirmationJson, new TypeReference<>() {});

            String purchaserEmail = (String) confimationMap.get("purchaserEmail");
            String sellerEmail = (String) confimationMap.get("sellerEmail");
            String propertyTitle = (String) confimationMap.get("propertyTitle");
            String propertyLocation = (String) confimationMap.get("propertyLocation");
            Boolean confirmationStatus = (Boolean) confimationMap.get("confirmationStatus");

            YourPropertyUser purchaser = userRepository.findByEmail(purchaserEmail);
            YourPropertyUser seller = userRepository.findByEmail(sellerEmail);
            Optional<Property> property = propertyRepository.findByTitleAndAddress(propertyTitle, propertyLocation);

            Optional<PurchaseConfirmation> existingConfirmation = purchaseConfirmationRepository.findPurchaseConfirmationByPurchaserAndSellerAndProperty(purchaser, seller, property.get());

            if(existingConfirmation.isPresent()) {
                PurchaseConfirmation confirmation = existingConfirmation.get();
                confirmation.setConfirmed(confirmationStatus);
                purchaseConfirmationRepository.save(confirmation);
            } else {
                PurchaseConfirmation confirmation = new PurchaseConfirmation();
                confirmation.setPurchaser(purchaser);
                confirmation.setSeller(seller);
                confirmation.setProperty(property.get());
                confirmation.setConfirmed(confirmationStatus);
                if(property.isPresent()) {
                    confirmation.setForSale(property.get().getOfferType().equals("For sale"));
                }
                purchaseConfirmationRepository.save(confirmation);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true)
    public Optional<PurchaseConfirmation> getConfirmation(String confirmationJson) {
        try {
            // Parse the JSON
            Map<String, Object> confimationMap = mapper.readValue(confirmationJson, new TypeReference<>() {});

            String purchaserEmail = (String) confimationMap.get("purchaserEmail");
            String sellerEmail = (String) confimationMap.get("sellerEmail");
            String propertyTitle = (String) confimationMap.get("propertyTitle");
            String propertyLocation = (String) confimationMap.get("propertyLocation");

            YourPropertyUser purchaser = userRepository.findByEmail(purchaserEmail);
            YourPropertyUser seller = userRepository.findByEmail(sellerEmail);
            Optional<Property> property = propertyRepository.findByTitleAndAddress(propertyTitle, propertyLocation);

            return purchaseConfirmationRepository.findPurchaseConfirmationByPurchaserAndSellerAndProperty(purchaser, seller, property.get());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
