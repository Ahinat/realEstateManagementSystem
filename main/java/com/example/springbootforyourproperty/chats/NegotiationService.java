package com.example.springbootforyourproperty.chats;

import com.example.springbootforyourproperty.yourproperty.Property;
import com.example.springbootforyourproperty.yourproperty.PropertyRepository;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NegotiationService {

    @Autowired
    private NegotiationRepository negotiationRepository;

    @Autowired
    private YourPropertyUserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Transactional
    public Map<String, Object> offerNegotiation(String negotiationJson) {
        try {
            // Parse the JSON
            Map<String, Object> negotiationOffer = mapper.readValue(negotiationJson, new TypeReference<>() {});

            String negotiatorEmail = (String) negotiationOffer.get("negotiatorEmail");
            String sellerEmail = (String) negotiationOffer.get("recipientEmail");
            String propertyTitle = (String) negotiationOffer.get("propertyTitle");
            String propertyLocation = (String) negotiationOffer.get("propertyLocation");

            YourPropertyUser negotiator = userRepository.findByEmail(negotiatorEmail);
            YourPropertyUser seller = userRepository.findByEmail(sellerEmail);
            Optional<Property> property = propertyRepository.findByTitleAndAddress(propertyTitle, propertyLocation);

            Negotiation negotiation = new Negotiation();
            negotiation.setNegotiator(negotiator);
            negotiation.setRecipient(seller);
            negotiation.setProperty(property.get());

            if(negotiationOffer.containsKey("salePrice")) negotiation.setNegotiatedSalePrice(negotiationOffer.get("salePrice") != null ? new BigDecimal(String.valueOf(negotiationOffer.get("salePrice"))) : null);
            if(negotiationOffer.containsKey("rentPrice")) negotiation.setNegotiatedRentPrice(negotiationOffer.get("rentPrice") != null ? new BigDecimal(String.valueOf(negotiationOffer.get("rentPrice"))) : null);
            if(negotiationOffer.containsKey("rentTerm")) negotiation.setNegotiatedRentTerm(negotiationOffer.get("rentTerm") != null ? (String) negotiationOffer.get("rentTerm") : null);

            negotiation.setStatus(Negotiation.Status.OFFERED);
            negotiation.setOfferedAt(LocalDateTime.now());

            Negotiation savedNegotiation = negotiationRepository.save(negotiation);

            Map<String, Object> result = new HashMap<>();
            result.put("type", "Negotiation");
            result.put("negotiationId", savedNegotiation.getNegotiationId());
            result.put("negotiator", savedNegotiation.getNegotiator().getEmail());
            result.put("recipient", savedNegotiation.getRecipient().getEmail());
            String offerType = savedNegotiation.getProperty().getOfferType();
            result.put("propertyOfferType", offerType);
            if ("For sale".equals(offerType)) {
                result.put("negotiatedSalePrice", savedNegotiation.getNegotiatedSalePrice());
            } else if ("For rent".equals(offerType)) {
                result.put("negotiatedRentPrice", savedNegotiation.getNegotiatedRentPrice());
                result.put("negotiatedRentTerm", savedNegotiation.getNegotiatedRentTerm());
            }
            result.put("offeredAt", savedNegotiation.getOfferedAt());
            result.put("propertyTitle", property.get().getPropertyTitle());
            result.put("propertyLocation", property.get().getStreet() + ", " + property.get().getCity() + ", " + property.get().getState() + ", " + property.get().getPostalCode() + ", " + property.get().getCountry());
            result.put("status", savedNegotiation.getStatus());

            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    public Negotiation offerNegotiation(Long buyerOrRenterId, Long sellerId, Long propertyId, BigDecimal negotiatedSalePrice, BigDecimal negotiatedRentPrice, String negotiatedRentTerm) {
//        YourPropertyUser buyerOrRenter = userRepository.findById(buyerOrRenterId)
//                .orElseThrow(() -> new IllegalArgumentException("Buyer or Renter not found"));
//
//        YourPropertyUser seller = userRepository.findById(sellerId)
//                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));
//
//        Property property = propertyRepository.findById(propertyId)
//                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
//
//        Negotiation negotiation = new Negotiation();
//        negotiation.setNegotiator(buyerOrRenter);
//        negotiation.setRecipient(seller);
//        negotiation.setProperty(property);
//        if (property.getOfferType().equals("For sale")) {
//            negotiation.setNegotiatedSalePrice(negotiatedSalePrice);
//        } else if (property.getOfferType().equals("For rent")) {
//            negotiation.setNegotiatedRentPrice(negotiatedRentPrice);
//            negotiation.setNegotiatedRentTerm(negotiatedRentTerm);
//        }
//        negotiation.setStatus(Negotiation.Status.OFFERED);
//        negotiation.setOfferedAt(LocalDateTime.now());
//
//        return negotiationRepository.save(negotiation);
//    }

    public List<Negotiation> getNegotiationsForBuyerOrRenter(Long buyerOrRenterId, Long propertyId) {
        YourPropertyUser buyerOrRenter = userRepository.findById(buyerOrRenterId)
                .orElseThrow(() -> new IllegalArgumentException("Buyer or Renter not found"));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        return negotiationRepository.findByNegotiatorAndProperty(buyerOrRenter, property);
    }

    public List<Negotiation> getNegotiationsForSeller(Long sellerId, Long propertyId) {
        YourPropertyUser seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        return negotiationRepository.findByRecipientAndProperty(seller, property);
    }

    @Transactional
    public Negotiation.Status updateNegotiationStatus(String updateStatusJson) {
        try {
            // Parse the JSON
            Map<String, Object> updateStatus = mapper.readValue(updateStatusJson, new TypeReference<>() {});

            Object negotiationIdObj = updateStatus.get("negotiationId");
            Long negotiationId;
            if (negotiationIdObj instanceof Integer) {
                negotiationId = ((Integer) negotiationIdObj).longValue();
            } else if (negotiationIdObj instanceof Long) {
                negotiationId = (Long) negotiationIdObj;
            } else {
                throw new IllegalArgumentException("Invalid negotiationId type");
            }

            Negotiation.Status status = Negotiation.Status.valueOf(updateStatus.get("status").toString());

            // Find the negotiation being updated
            Optional<Negotiation> negotiationOpt = negotiationRepository.findById(negotiationId);
            if (negotiationOpt.isEmpty()) {
                throw new IllegalArgumentException("Negotiation not found with ID: " + negotiationId);
            }

            Negotiation negotiation = negotiationOpt.get();

            if (status == Negotiation.Status.ACCEPTED) {
                // Find and update other negotiations for the same property and users
                List<Negotiation> otherNegotiations = negotiationRepository.findByPropertyAndUsers(
                        negotiation.getProperty().getPropertyId(),
                        negotiation.getNegotiator().getEmail(),
                        negotiation.getRecipient().getEmail()
                );

                for (Negotiation other : otherNegotiations) {
                    if (!other.getNegotiationId().equals(negotiationId) && other.getStatus() == Negotiation.Status.ACCEPTED) {
                        other.setStatus(Negotiation.Status.OFFERED);
                        negotiationRepository.save(other);
                    }
                }
            }

            // Update the current negotiation
            negotiation.setStatus(status);
            negotiationRepository.save(negotiation);

            return status;
//            negotiationRepository.updateNegotiationStatus(negotiationId, status);
//            return status;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}