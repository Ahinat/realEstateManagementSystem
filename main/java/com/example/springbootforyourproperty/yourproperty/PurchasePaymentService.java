package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyseller.SellerProfile;
import com.example.springbootforyourproperty.yourpropertyseller.SellerProfileRepository;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchasePaymentService {
    @Autowired
    private PurchasePaymentRepository purchasePaymentRepository;

    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    @Autowired
    private SellerProfileRepository sellerProfileRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Transactional
    public PurchasePayment addPurchasePayment(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Map<String, String> paymentMap = objectMapper.readValue(jsonString, new TypeReference<>() {});

            String purchaserEmail = paymentMap.get("purchaserEmail");
            String sellerEmail = paymentMap.get("sellerEmail");
            String propertyTitle = paymentMap.get("propertyTitle");
            String propertyLocation = paymentMap.get("propertyLocation");
            String cardType = paymentMap.get("cardType");
            String cardNumber = paymentMap.get("cardNumber");
            String expiryMonth = paymentMap.get("expiryMonth");
            Integer expiryYear = Integer.valueOf(paymentMap.get("expiryYear"));
            String securityCode = paymentMap.get("securityCode");
            String nameOnCard = paymentMap.get("nameOnCard");

            YourPropertyUser purchaser = yourPropertyUserRepository.findByEmail(purchaserEmail);
            YourPropertyUser seller = yourPropertyUserRepository.findByEmail(sellerEmail);
            Optional<Property> property = propertyRepository.findByTitleAndAddress(propertyTitle, propertyLocation);

            PurchasePayment purchasePayment = new PurchasePayment();
            purchasePayment.setPurchaser(purchaser);
            purchasePayment.setSeller(seller);
            purchasePayment.setProperty(property.get());
            purchasePayment.setCardType(cardType);
            purchasePayment.setCardNumber(cardNumber);
            purchasePayment.setExpiryMonth(expiryMonth);
            purchasePayment.setExpiryYear(expiryYear);
            purchasePayment.setSecurityCode(securityCode);
            purchasePayment.setNameOnCard(nameOnCard);
            purchasePaymentRepository.save(purchasePayment);

            if(property.get().getOfferType().equals("For sale")) {
                SellerProfile sellerProfile = sellerProfileRepository.findByYourPropertyUser(purchaser);
                property.get().setSellerProfile(sellerProfile);
                propertyRepository.save(property.get());
            }

            return purchasePayment;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<Map<String, Object>> getRentedProperties(String email){
        YourPropertyUser seller = yourPropertyUserRepository.findByEmail(email);

        return purchasePaymentRepository.findBySeller(seller).stream().map(paymentPurchase -> {
            Map<String, Object> result = new HashMap<>();

            YourPropertyUser renter = paymentPurchase.getPurchaser();
            result.put("email", renter.getEmail());
            result.put("name", renter.getFirstName() + " " + renter.getLastName());
            result.put("profilePicture", renter.getProfilePicture());

            Property property = paymentPurchase.getProperty();
            result.put("picture", property.getPicture().getFirst());
            result.put("title", property.getPropertyTitle());
            result.put("address", property.getStreet() + ", " + property.getCity() + ", " + property.getState() + ", " + property.getPostalCode() + ", " + property.getCountry());
            result.put("type" , property.getPropertyType());
            result.put("offerType", property.getOfferType());
            if(property.getOfferType().equals("For sale"))
                result.put("price", property.getSalePrice());
            else if(property.getOfferType().equals("For rent")){
                result.put("price", property.getRentPrice());
                result.put("term", property.getRentTerm());
            }
            return result;
        }).collect(Collectors.toList());
    }

    @Transactional
    public List<Map<String, Object>> getRentalProperties(String email){
        YourPropertyUser purchaser = yourPropertyUserRepository.findByEmail(email);

        return purchasePaymentRepository.findByPurchaser(purchaser).stream().map(paymentPurchase -> {
            Map<String, Object> result = new HashMap<>();

            YourPropertyUser seller = paymentPurchase.getSeller();
            result.put("email", seller.getEmail());
            result.put("name", seller.getFirstName() + " " + seller.getLastName());
            result.put("profilePicture", seller.getProfilePicture());

            Property property = paymentPurchase.getProperty();
            result.put("picture", property.getPicture().getFirst());
            result.put("title", property.getPropertyTitle());
            result.put("address", property.getStreet() + ", " + property.getCity() + ", " + property.getState() + ", " + property.getPostalCode() + ", " + property.getCountry());
            result.put("type" , property.getPropertyType());
            result.put("offerType", property.getOfferType());
            if(property.getOfferType().equals("For sale"))
                result.put("price", property.getSalePrice());
            else if(property.getOfferType().equals("For rent")){
                result.put("price", property.getRentPrice());
                result.put("term", property.getRentTerm());
            }
            return result;
        }).collect(Collectors.toList());
    }
}
