package com.example.springbootforyourproperty.yourpropertyrenter;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RenterProfileService {
    @Autowired
    private RenterProfileRepository renterProfileRepository;

    @Autowired
    private RenterApartmentFeaturesRepository renterApartmentFeaturesRepository;

    @Autowired
    private RenterHouseFeaturesRepository renterHouseFeaturesRepository;
    
    @Autowired
    private RenterLandFeaturesRepository renterLandFeaturesRepository;

    @Autowired
    private RenterResidentialUnitFeaturesRepository renterResidentialUnitFeaturesRepository;
    
    @Autowired
    private RenterCommercialUnitFeaturesRepository renterCommercialUnitFeaturesRepository;
    
    @Autowired 
    private RenterBusinessPlaceFeaturesRepository renterBusinessPlaceFeaturesRepository;
    
    @Autowired 
    private YourPropertyUserRepository yourPropertyUserRepository;

    @Transactional
    public RenterProfile createRenterProfile(JsonNode renterProfile) {
        String userEmail = renterProfile.get("email").asText();

        // Find the user in main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(userEmail);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + userEmail + " not found");
        }

        // Check if a RenterProfile is already associated with this user
        RenterProfile existingRenterProfile = renterProfileRepository.findByYourPropertyUser(yourPropertyUser);
        if (existingRenterProfile != null) {
            throw new RuntimeException("A RenterProfile already exists for this user");
        }

        RenterProfile renter = new RenterProfile();
        renter.setYourPropertyUser(yourPropertyUser);

        BigDecimal minBudget = new BigDecimal(renterProfile.get("minBudget").asText());
        BigDecimal maxBudget = new BigDecimal(renterProfile.get("maxBudget").asText());
        String moveInDate = renterProfile.get("moveInDate").asText();
        String rentalTerm = renterProfile.get("rentalTerm").asText();

        List<String> propertyTypes = new ArrayList<>();
        for(JsonNode property : renterProfile.get("propertyTypes")) {
            propertyTypes.add(property.asText());
        }
        List<String> locations = new ArrayList<>();
        for(JsonNode location : renterProfile.get("locations")) {
            locations.add(location.asText());
        }

        renter.setMinBudget(minBudget);
        renter.setMaxBudget(maxBudget);
        renter.setMoveInDate(moveInDate);
        renter.setRentalTerm(rentalTerm);
        renter.setPreferredPropertyTypes(propertyTypes);
        renter.setPreferredLocations(locations);

        // addProperty the renter profile
        renter = renterProfileRepository.save(renter);

        if(renterProfile.has("ApartmentFeatures")) {
            JsonNode apartmentFeatures = renterProfile.get("ApartmentFeatures");
            RenterApartmentFeatures renterApartmentFeatures = new RenterApartmentFeatures();
            renterApartmentFeatures.setPetPolicy(apartmentFeatures.get("petPolicy").asBoolean());
            renterApartmentFeatures.setFurnished(apartmentFeatures.get("furnished").asBoolean());
            renterApartmentFeatures.setParkingAvailability(apartmentFeatures.get("parkingAvailability").asBoolean());
            renterApartmentFeatures.setTerraceAvailability(apartmentFeatures.get("terraceAvailability").asBoolean());
            renterApartmentFeatures.setRenter(renter);



            renterApartmentFeaturesRepository.save(renterApartmentFeatures);
        }

        if(renterProfile.has("HouseFeatures")) {
            JsonNode houseFeatures = renterProfile.get("HouseFeatures");

            RenterHouseFeatures renterHouseFeatures = new RenterHouseFeatures();
            renterHouseFeatures.setRenter(renter);
            renterHouseFeatures.setBasement(houseFeatures.get("basement").asText());
            renterHouseFeatures.setGarage(houseFeatures.get("garage").asBoolean());
            List<String> securityFeatures = new ArrayList<>();
            for(JsonNode securityFeature : houseFeatures.get("securityFeatures")) {
                securityFeatures.add(securityFeature.asText());
            }
            renterHouseFeatures.setSecurity_features(securityFeatures);

            renterHouseFeaturesRepository.save(renterHouseFeatures);
        }

        if(renterProfile.has("LandFeatures")) {
            JsonNode landFeatures = renterProfile.get("LandFeatures");

            RenterLandFeatures renterLandFeatures = new RenterLandFeatures();
            renterLandFeatures.setRenter(renter);
            renterLandFeatures.setTopography(landFeatures.get("topography").asText());
            renterLandFeatures.setRoadAccess(landFeatures.get("roadAccess").asText());
            List<String> utilities = new ArrayList<>();
            for(JsonNode utility : landFeatures.get("utilities")) {
                utilities.add(utility.asText());
            }
            renterLandFeatures.setUtilites(utilities);

            renterLandFeaturesRepository.save(renterLandFeatures);
        }

        if(renterProfile.has("ResidentialUnitFeatures")) {
            JsonNode residentialUnitFeatures = renterProfile.get("ResidentialUnitFeatures");

            RenterResidentialUnitFeatures renterResidentialUnitFeatures = new RenterResidentialUnitFeatures();
            renterResidentialUnitFeatures.setRenter(renter);
            renterResidentialUnitFeatures.setNoiseInsulation(residentialUnitFeatures.get("noiseInsulation").asBoolean());
            renterResidentialUnitFeatures.setStorageSpace(residentialUnitFeatures.get("storageSpace").asText());
            List<String> amenities = new ArrayList<>();
            for(JsonNode amenity : residentialUnitFeatures.get("amenities")) {
                amenities.add(amenity.asText());
            }
            renterResidentialUnitFeatures.setAmenities(amenities);

            renterResidentialUnitFeaturesRepository.save(renterResidentialUnitFeatures);
        }

        if(renterProfile.has("CommercialUnitFeatures")) {
            JsonNode commercialUnitFeatures = renterProfile.get("CommercialUnitFeatures");

            RenterCommercialUnitFeatures renterCommercialUnitFeatures = new RenterCommercialUnitFeatures();
            renterCommercialUnitFeatures.setRenter(renter);
            renterCommercialUnitFeatures.setFloorLayout(commercialUnitFeatures.get("floorLayout").asText());
            List<String> purposes = new ArrayList<>();
            for(JsonNode purpose : commercialUnitFeatures.get("purposes")) {
                purposes.add(purpose.asText());
            }
            renterCommercialUnitFeatures.setPurpose(purposes);

            List<String> accessibilities = new ArrayList<>();
            for(JsonNode accessibility : commercialUnitFeatures.get("accessibilities")) {
                accessibilities.add(accessibility.asText());
            }
            renterCommercialUnitFeatures.setAccessibility(accessibilities);

            renterCommercialUnitFeaturesRepository.save(renterCommercialUnitFeatures);
        }

        if(renterProfile.has("BusinessPlaceFeatures")) {
            JsonNode businessPlaceFeatures = renterProfile.get("BusinessPlaceFeatures");

            RenterBusinessPlaceFeatures renterBusinessPlaceFeatures = new RenterBusinessPlaceFeatures();
            renterBusinessPlaceFeatures.setRenter(renter);
            renterBusinessPlaceFeatures.setVentilation(businessPlaceFeatures.get("ventilation").asText());
            List<String> purposes = new ArrayList<>();
            for(JsonNode purpose : businessPlaceFeatures.get("purposes")) {
                purposes.add(purpose.asText());
            }
            renterBusinessPlaceFeatures.setPurpose(purposes);

            List<String> powerSupplies = new ArrayList<>();
            for(JsonNode powerSupply : businessPlaceFeatures.get("powerSupplies")) {
                powerSupplies.add(powerSupply.asText());
            }
            renterBusinessPlaceFeatures.setPowerSupply(powerSupplies);

            renterBusinessPlaceFeaturesRepository.save(renterBusinessPlaceFeatures);
        }

        return renterProfileRepository.save(renter);
    }


    // Method to retrieve full Renter Profile by email
    @Transactional
    public RenterProfile getRenterProfileByEmail(String email) {
        // Find the user in the main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + email + " not found");
        }

        RenterProfile renter = renterProfileRepository.findByYourPropertyUser(yourPropertyUser);
        if (renter == null) {
            return null;
        }
        return renter;
    }
}
