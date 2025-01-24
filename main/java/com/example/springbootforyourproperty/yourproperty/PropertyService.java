package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyseller.SellerProfile;
import com.example.springbootforyourproperty.yourpropertyseller.SellerProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private SellerProfileService sellerProfileService;

    @Autowired
    private EntityManager entityManager;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // Save a new propertyJson
    @Transactional
    public Property addProperty(String propertyJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Parse the JSON
            Map<String, Object> propertyData = objectMapper.readValue(propertyJson, new TypeReference<>() {});

            String userEmail = (String) propertyData.get("userEmail");

            // Find the seller in the main table
            SellerProfile sellerProfile = sellerProfileService.getSellerProfileByEmail(userEmail);

            if (sellerProfile == null) {
                // If user doesn't have a seller profile, throw an error or handle it
                throw new RuntimeException("User with email " + userEmail + " does not have a seller profile");
            }

            String offerType = (String) propertyData.get("offerType");
            String propertyType = (String) propertyData.get("propertyType");

            // Dynamically create the appropriate subclass
            Property property = createPropertyInstance(propertyType, propertyData, null);

            // Set common fields
            updateCommonPropertyFields(property, propertyData);

            // Set seller profile
            property.setSellerProfile(sellerProfile);

            // Set common properties
//            property.setSellerProfile(sellerProfile);
//            property.setOfferType(offerType);
//            property.setPropertyType(propertyType);
//            property.setPropertyTitle((String) propertyData.get("propertyTitle"));
//            property.setPropertyDescription((String) propertyData.get("propertyDescription"));
//            if ("For sale".equals(offerType)) {
//                property.setSalePrice(new BigDecimal(String.valueOf(propertyData.get("salePrice"))));
//            } else if ("For rent".equals(offerType)) {
//                property.setRentPrice(new BigDecimal(String.valueOf(propertyData.get("rentPrice"))));
//                property.setRentTerm((String) propertyData.get("rentTerm"));
//                property.setRentAvailableDate((String) propertyData.get("rentAvailableDate"));
//            }
//            property.setCountry((String) propertyData.get("country"));
//            property.setCity((String) propertyData.get("city"));
//            property.setState((String) propertyData.get("state"));
//            property.setPostalCode((String) propertyData.get("postalCode"));
//            property.setStreet((String) propertyData.get("street"));
//            property.setPicture((List<String>) propertyData.get("pictures"));

            // Save the property
            return propertyRepository.save(property);
        } catch (Exception e) {
            throw new RuntimeException("Error saving property: " + e.getMessage(), e);
        }
    }

    private void updateCommonPropertyFields(Property property, Map<String, Object> propertyData) {
        if (propertyData.containsKey("offerType"))
            property.setOfferType((String) propertyData.get("offerType"));
        if (propertyData.containsKey("propertyTitle"))
            property.setPropertyTitle((String) propertyData.get("propertyTitle"));
        if (propertyData.containsKey("propertyDescription"))
            property.setPropertyDescription((String) propertyData.get("propertyDescription"));
        if (propertyData.containsKey("salePrice"))
            property.setSalePrice(propertyData.get("salePrice") != null ?
                    new BigDecimal(String.valueOf(propertyData.get("salePrice"))) : null);
        if (propertyData.containsKey("rentPrice"))
            property.setRentPrice(propertyData.get("rentPrice") != null ?
                    new BigDecimal(String.valueOf(propertyData.get("rentPrice"))) : null);
        if (propertyData.containsKey("rentTerm"))
            property.setRentTerm((String) propertyData.get("rentTerm"));
        if (propertyData.containsKey("rentAvailableDate"))
            property.setRentAvailableDate((String) propertyData.get("rentAvailableDate"));
        if (propertyData.containsKey("country"))
            property.setCountry((String) propertyData.get("country"));
        if (propertyData.containsKey("city"))
            property.setCity((String) propertyData.get("city"));
        if (propertyData.containsKey("state"))
            property.setState((String) propertyData.get("state"));
        if (propertyData.containsKey("postalCode"))
            property.setPostalCode((String) propertyData.get("postalCode"));
        if (propertyData.containsKey("street"))
            property.setStreet((String) propertyData.get("street"));
        if (propertyData.containsKey("picture"))
            property.setPicture((List<String>) propertyData.get("picture"));
    }


    private Property createPropertyInstance(String propertyType, Map<String, Object> propertyData, Property existingProperty) {
        switch (propertyType) {
            case "Apartment":
                Apartment apartment = existingProperty instanceof Apartment ? (Apartment) existingProperty : new Apartment();
                Map<String, Object> apartmentFeatures = (Map<String, Object>) propertyData.get("apartmentFeatures");

                // key features
                apartment.setPetPolicy((Boolean) apartmentFeatures.getOrDefault("petPolicy", apartment.getPetPolicy()));
                apartment.setFurniturePolicy((Boolean) apartmentFeatures.getOrDefault("furniturePolicy", apartment.getFurniturePolicy()));
                apartment.setTerracePolicy((Boolean) apartmentFeatures.getOrDefault("terracePolicy", apartment.getTerracePolicy()));
                apartment.setParkingPolicy((Boolean) apartmentFeatures.getOrDefault("parkingPolicy", apartment.getParkingPolicy()));

                // specific features
                apartment.setBedrooms((Integer) apartmentFeatures.getOrDefault("bedrooms", apartment.getBedrooms()));
                apartment.setBathrooms((Integer) apartmentFeatures.getOrDefault("bathrooms", apartment.getBathrooms()));
                apartment.setFloor((Integer) apartmentFeatures.getOrDefault("floor", apartment.getFloor()));
                apartment.setTotalFloor((Integer) apartmentFeatures.getOrDefault("totalFloor", apartment.getTotalFloor()));
                apartment.setMaintenanceServices((String) apartmentFeatures.getOrDefault("maintenanceServices", apartment.getMaintenanceServices()));
                apartment.setBalconyPolicy((Boolean) apartmentFeatures.getOrDefault("balconyPolicy", apartment.getBalconyPolicy()));

                return apartment;

            case "House":
                House house = existingProperty instanceof House ? (House) existingProperty : new House();
                Map<String, Object> houseFeatures = (Map<String, Object>) propertyData.get("houseFeatures");

                // key features
                house.setBasementAvailability((String) houseFeatures.getOrDefault("basementAvailability", house.getBasementAvailability()));
                house.setSecurityFeatures((List<String>) houseFeatures.getOrDefault("securityFeatures", house.getSecurityFeatures()));
                house.setGarageAvailability((Boolean) houseFeatures.getOrDefault("garageAvailability", house.getGarageAvailability()));

                // specific features
                house.setBedrooms((Integer) houseFeatures.getOrDefault("bedrooms", house.getBedrooms()));
                house.setBathrooms((Integer) houseFeatures.getOrDefault("bathrooms", house.getBathrooms()));
                house.setFloor((Integer) houseFeatures.getOrDefault("floor", house.getFloor()));
                house.setRoofType((String) houseFeatures.getOrDefault("roofType", house.getRoofType()));
                house.setGardenAvailability((Boolean) houseFeatures.getOrDefault("gardenAvailability", house.isGardenAvailability()));
                house.setBalconyAvailability((Boolean) houseFeatures.getOrDefault("balconyAvailability", house.isBalconyAvailability()));

                return house;

            case "Land":
                Land land = existingProperty instanceof Land ? (Land) existingProperty : new Land();
                Map<String, Object> landFeatures = (Map<String, Object>) propertyData.get("landFeatures");

                // key features
                land.setTopography((String) landFeatures.getOrDefault("topography", land.getTopography()));
                land.setUtilities((List<String>) landFeatures.getOrDefault("utilities", land.getUtilities()));
                land.setRoadAccess((String) landFeatures.getOrDefault("roadAccess", land.getRoadAccess()));

                // specific features
                land.setType((String) landFeatures.getOrDefault("type", land.getType()));
                if (landFeatures.containsKey("length"))
                    land.setLength(new BigDecimal(String.valueOf(landFeatures.getOrDefault("length", land.getLength()))));
                if (landFeatures.containsKey("width"))
                    land.setWidth(new BigDecimal(String.valueOf(landFeatures.getOrDefault("width", land.getWidth()))));
                if (landFeatures.containsKey("area"))
                    land.setArea(new BigDecimal(String.valueOf(landFeatures.getOrDefault("area", land.getArea()))));
                land.setBorder((String) landFeatures.getOrDefault("border", land.getBorder()));
                land.setPreviousDevelopment((Boolean) landFeatures.getOrDefault("previousDevelopment", land.isPreviousDevelopment()));
                land.setNearbyInfrastructure((List<String>) landFeatures.getOrDefault("nearbyInfrastructure", land.getNearbyInfrastructure()));

                return land;

            case "ResidentialUnit":
                ResidentialUnit residentialUnit = existingProperty instanceof ResidentialUnit ? (ResidentialUnit) existingProperty : new ResidentialUnit();
                Map<String, Object> residentialUnitFeatures = (Map<String, Object>) propertyData.get("residentialUnitFeatures");

                // key features
                residentialUnit.setAmenities((List<String>) residentialUnitFeatures.getOrDefault("amenities", residentialUnit.getAmenities()));
                residentialUnit.setStorageType((String) residentialUnitFeatures.getOrDefault("storageType", residentialUnit.getStorageType()));
                residentialUnit.setNoiseInsulation((Boolean) residentialUnitFeatures.getOrDefault("noiseInsulation", residentialUnit.getNoiseInsulation()));

                // specific features
                residentialUnit.setConfiguration((Integer) residentialUnitFeatures.getOrDefault("configuration", residentialUnit.getConfiguration()));
                residentialUnit.setSharedFacilities((List<String>) residentialUnitFeatures.getOrDefault("sharedFacilities", residentialUnit.getSharedFacilities()));
                residentialUnit.setOrientation((String) residentialUnitFeatures.getOrDefault("orientation", residentialUnit.getOrientation()));
                residentialUnit.setNaturalLight((String) residentialUnitFeatures.getOrDefault("naturalLight", residentialUnit.getNaturalLight()));

                return residentialUnit;

            case "CommercialUnit":
                CommercialUnit commercialUnit = existingProperty instanceof CommercialUnit ? (CommercialUnit) existingProperty : new CommercialUnit();
                Map<String, Object> commercialUnitFeatures = (Map<String, Object>) propertyData.get("commercialUnitFeatures");

                // key features
                commercialUnit.setPurpose((List<String>) commercialUnitFeatures.getOrDefault("purpose", commercialUnit.getPurpose()));
                commercialUnit.setFloorLayout((String) commercialUnitFeatures.getOrDefault("floorLayout", commercialUnit.getFloorLayout()));
                commercialUnit.setAccessibilityFeatures((List<String>) commercialUnitFeatures.getOrDefault("accessibilityFeatures", commercialUnit.getAccessibilityFeatures()));

                // specific features
                commercialUnit.setFacade((String) commercialUnitFeatures.getOrDefault("facade", commercialUnit.getFacade()));
                commercialUnit.setNearbyAttractions((List<String>) commercialUnitFeatures.getOrDefault("nearbyAttractions", commercialUnit.getNearbyAttractions()));
                commercialUnit.setFireSafety((List<String>) commercialUnitFeatures.getOrDefault("fireSafety", commercialUnit.getFireSafety()));
                commercialUnit.setAirCondition((String) commercialUnitFeatures.getOrDefault("airCondition", commercialUnit.getAirCondition()));

                return commercialUnit;

            case "BusinessPlace":
                BusinessPlace businessPlace = existingProperty instanceof BusinessPlace ? (BusinessPlace) existingProperty : new BusinessPlace();
                Map<String, Object> businessPlaceFeatures = (Map<String, Object>) propertyData.get("businessPlaceFeatures");

                // key features
                businessPlace.setPurpose((List<String>) businessPlaceFeatures.getOrDefault("purpose", businessPlace.getPurpose()));
                businessPlace.setVentilation((String) businessPlaceFeatures.getOrDefault("ventilation", businessPlace.getVentilation()));
                businessPlace.setPowerSupply((List<String>) businessPlaceFeatures.getOrDefault("powerSupply", businessPlace.getPowerSupply()));

                // specific features
                businessPlace.setLightingSetup((String) businessPlaceFeatures.getOrDefault("lightingSetup", businessPlace.getLightingSetup()));
                businessPlace.setWaitingArea((String) businessPlaceFeatures.getOrDefault("waitingArea", businessPlace.getWaitingArea()));
                businessPlace.setVisibilityFromRoad((String) businessPlaceFeatures.getOrDefault("visibilityFromRoad", businessPlace.getVisibilityFromRoad()));
                businessPlace.setInsuranceDetails((String) businessPlaceFeatures.getOrDefault("insuranceDetails", businessPlace.getInsuranceDetails()));

                return businessPlace;

            default:
                throw new RuntimeException("Invalid property type: " + propertyType);
        }
    }

    @Transactional
    public Property updateProperty(String propertyJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Parse the JSON
            Map<String, Object> propertyData = objectMapper.readValue(propertyJson, new TypeReference<>() {});

            Long propertyId = ((Number) propertyData.get("propertyId")).longValue();
            Property existingProperty = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property with ID " + propertyId + " not found."));

            String propertyType = existingProperty.getPropertyType();

            updateCommonPropertyFields(existingProperty, propertyData);

            createPropertyInstance(propertyType, propertyData, existingProperty);

            return propertyRepository.save(existingProperty);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException("Error updating property: " + e.getMessage(), e);
        }
    }


    // Add photos (URLs) to an existing property
    public Property addPhotos(Long propertyId, List<String> photoUrls) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with ID: " + propertyId));
        property.getPicture().addAll(photoUrls);
        return propertyRepository.save(property);
    }

    // Get a property by ID
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found with ID: " + id));
    }

    // Get all properties (optional pagination can be added)
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Find all properties by seller ID
    @Transactional
    public List<Property> findPropertiesBySellerProfile(SellerProfile seller) {
        return propertyRepository.findBySellerProfile(seller);
    }

//    public boolean deleteProperty(String email, String title, String address) {
//        Optional<Property> propertyOptional = propertyRepository.findByEmailAndTitleAndAddress(email, title, address);
//        if (propertyOptional.isPresent()) {
//            propertyRepository.delete(propertyOptional.get());
//            return true;
//        } else {
//            return false;
//        }
//    }

    public boolean deleteProperty(String title, String address) {
        Optional<Property> propertyOptional = propertyRepository.findByTitleAndAddress(title, address);
        if (propertyOptional.isPresent()) {
            propertyRepository.delete(propertyOptional.get());
            return true;
        } else {
            return false;
        }
    }

    public Property getProperty(String title, String address) {
        Optional<Property> propertyOptional = propertyRepository.findByTitleAndAddress(title, address);
        return propertyOptional.orElse(null);
    }

    public Map<String, Object> getPropertySeller(String title, String address) {
        Optional<Property> propertyOptional = propertyRepository.findByTitleAndAddress(title, address);
        if (propertyOptional.isPresent()) {
            Map<String, Object> propertyData = new HashMap<>();
            propertyData.put("sellerEmail", propertyOptional.get().getSellerProfile().getYourPropertyUser().getEmail());
            propertyData.put("sellerFirstName", propertyOptional.get().getSellerProfile().getYourPropertyUser().getFirstName());
            propertyData.put("sellerLastName", propertyOptional.get().getSellerProfile().getYourPropertyUser().getLastName());
            propertyData.put("sellerPicture", propertyOptional.get().getSellerProfile().getYourPropertyUser().getProfilePicture());
            return propertyData;
        }
        return null;
    }

    // Fetch all properties with pagination
    public Page<Property> getProperties(Pageable pageable) {
//        Sort sort = Sort.by("")
        return propertyRepository.findAll(pageable);
    }

    // Fetch properties filtered by property type with pagination
    public Page<Property> getPropertiesByCategory(Pageable pageable, String category) {
        return propertyRepository.findByPropertyType(category, pageable);
    }

    // Fetch properties filtered by offer type with pagination
    public Page<Property> getPropertiesByOfferType(Pageable pageable, String offerType) {
        return propertyRepository.findByOfferType(offerType, pageable);
    }

    // Fetch properties filtered by property type and offer type with pagination
    public Page<Property> getPropertiesByCategoryAndOfferType(Pageable pageable, String category, String offerType) {
        return propertyRepository.findByPropertyTypeAndOfferType(category, offerType, pageable);
    }

    // Fetch properties filtered by sale price or rent price with pagination
    public Page<Property> getPropertiesByPriceRange(Pageable pageable, BigDecimal minSalePrice, BigDecimal maxSalePrice, BigDecimal minRentPrice, BigDecimal maxRentPrice) {
        if(minSalePrice != null && maxSalePrice != null && minRentPrice != null && maxRentPrice != null)
            return propertyRepository.findBySalePriceBetweenAndRentPriceIsNullOrSalePriceIsNullAndRentPriceBetween(minSalePrice, maxSalePrice, minRentPrice, maxRentPrice, pageable);
        else if(minSalePrice != null && minRentPrice != null)
            return propertyRepository.findBySalePriceGreaterThanEqualAndRentPriceIsNullOrSalePriceIsNullAndRentPriceGreaterThanEqual(minSalePrice, minRentPrice, pageable);
        else if(maxSalePrice != null && maxRentPrice != null)
            return propertyRepository.findBySalePriceLessThanEqualAndRentPriceIsNullOrSalePriceIsNullAndRentPriceLessThanEqual(maxSalePrice, maxRentPrice, pageable);
        else
            return propertyRepository.findAll(pageable);
    }

    // Fetch properties filtered by property type and price range (sale or rent)
    public Page<Property> getPropertiesByCategoryAndPriceRange(Pageable pageable, String category, BigDecimal minSalePrice, BigDecimal maxSalePrice, BigDecimal minRentPrice, BigDecimal maxRentPrice) {
        if(minSalePrice != null && maxSalePrice != null && minRentPrice != null && maxRentPrice != null)
            return propertyRepository.findByPropertyTypeAndSalePriceBetweenAndRentPriceIsNullOrSalePriceIsNullAndRentPriceBetween(category, minSalePrice, maxSalePrice, minRentPrice, maxRentPrice, pageable);
        else if(minSalePrice != null && minRentPrice != null)
            return propertyRepository.findByPropertyTypeAndSalePriceGreaterThanEqualAndRentPriceIsNullOrSalePriceIsNullAndRentPriceGreaterThanEqual(category, minSalePrice, minRentPrice, pageable);
        else if(maxSalePrice != null && maxRentPrice != null)
            return propertyRepository.findByPropertyTypeAndSalePriceLessThanEqualAndRentPriceIsNullOrSalePriceIsNullAndRentPriceLessThanEqual(category, maxSalePrice, maxRentPrice, pageable);
        else
            return propertyRepository.findByPropertyType(category, pageable);
    }

    // Fetch properties filtered by property type, offer type, and price range (sale)
    public Page<Property> getPropertiesByCategoryAndOfferTypeAndSalePriceRange(Pageable pageable, String category, String offerType, BigDecimal minSalePrice, BigDecimal maxSalePrice) {
        if(minSalePrice != null && maxSalePrice != null)
            return propertyRepository.findByPropertyTypeAndOfferTypeAndSalePriceBetween(category, offerType, minSalePrice, maxSalePrice, pageable);
        else if(minSalePrice != null)
            return propertyRepository.findByPropertyTypeAndOfferTypeAndSalePriceGreaterThanEqual(category, offerType, minSalePrice, pageable);
        else if(maxSalePrice != null)
            return propertyRepository.findByPropertyTypeAndOfferTypeAndSalePriceLessThanEqual(category, offerType, maxSalePrice, pageable);
        else
            return propertyRepository.findByPropertyTypeAndOfferType(category, offerType, pageable);
    }

    // Fetch properties filtered by property type, offer type, and price range (rent)
    public Page<Property> getPropertiesByCategoryAndOfferTypeAndRentPriceRange(Pageable pageable, String category, String offerType, BigDecimal minRentPrice, BigDecimal maxRentPrice) {
        if(minRentPrice != null && maxRentPrice != null)
            return propertyRepository.findByPropertyTypeAndOfferTypeAndRentPriceBetween(category, offerType, minRentPrice, maxRentPrice, pageable);
        else if(minRentPrice != null)
            return propertyRepository.findByPropertyTypeAndOfferTypeAndRentPriceGreaterThanEqual(category, offerType, minRentPrice, pageable);
        else if(maxRentPrice != null)
            return propertyRepository.findByPropertyTypeAndOfferTypeAndRentPriceLessThanEqual(category, offerType, maxRentPrice, pageable);
        else
            return propertyRepository.findByPropertyTypeAndOfferType(category, offerType, pageable);
    }

    // Fetch properties filtered by offer type and sale price range
    public Page<Property> getPropertiesByOfferTypeAndSalePriceRange(Pageable pageable, String offerType, BigDecimal minSalePrice, BigDecimal maxSalePrice) {
        if(minSalePrice != null && maxSalePrice != null)
            return propertyRepository.findByOfferTypeAndSalePriceBetween(offerType, minSalePrice, maxSalePrice, pageable);
        else if(minSalePrice != null)
            return propertyRepository.findByOfferTypeAndSalePriceGreaterThanEqual(offerType, minSalePrice, pageable);
        else if(maxSalePrice != null)
            return propertyRepository.findByOfferTypeAndSalePriceLessThanEqual(offerType, maxSalePrice, pageable);
        else
            return propertyRepository.findByOfferType(offerType, pageable);
    }

    // Fetch properties filtered by offer type and rent price range
    public Page<Property> getPropertiesByOfferTypeAndRentPriceRange(Pageable pageable, String offerType, BigDecimal minRentPrice, BigDecimal maxRentPrice) {
        if(minRentPrice != null && maxRentPrice != null)
            return propertyRepository.findByOfferTypeAndRentPriceBetween(offerType, minRentPrice, maxRentPrice, pageable);
        else if(minRentPrice != null)
            return propertyRepository.findByOfferTypeAndRentPriceGreaterThanEqual(offerType, minRentPrice, pageable);
        else if(maxRentPrice != null)
            return propertyRepository.findByOfferTypeAndRentPriceLessThanEqual(offerType, maxRentPrice, pageable);
        else
            return propertyRepository.findByOfferType(offerType, pageable);
    }

    public List<Property> searchProperties(String searchText) {
        // Create the CriteriaBuilder Instance
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> cq = cb.createQuery(Property.class);
        Root<Property> property = cq.from(Property.class);

        Predicate predicate = cb.conjunction(); // Start with a true condition

        predicate = cb.and(predicate,
                cb.or(
                        cb.like(property.get("propertyTitle"), "%" + searchText + "%"),
                        cb.like(property.get("street"), "%" + searchText + "%"),
                        cb.like(property.get("city"), "%" + searchText + "%"),
                        cb.like(property.get("state"), "%" + searchText + "%"),
                        cb.like(property.get("country"), "%" + searchText + "%"),
                        cb.like(property.get("postalCode"), "%" + searchText + "%")
                )
        );

        cq.where(predicate);
        return entityManager.createQuery(cq).getResultList();
    }

//    public Page<Property> searchProperties(String searchText, int pageNumber, int pageSize) {
//        // Create the CriteriaBuilder Instance
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Property> cq = cb.createQuery(Property.class);
//        Root<Property> property = cq.from(Property.class);
//
//        // Start with a true condition (empty predicate)
//        Predicate predicate = cb.conjunction();
//
//        // Create the search conditions
//        predicate = cb.and(predicate,
//                cb.or(
//                        cb.like(property.get("propertyTitle"), "%" + searchText + "%"),
//                        cb.like(property.get("street"), "%" + searchText + "%"),
//                        cb.like(property.get("city"), "%" + searchText + "%"),
//                        cb.like(property.get("state"), "%" + searchText + "%"),
//                        cb.like(property.get("country"), "%" + searchText + "%"),
//                        cb.like(property.get("postalCode"), "%" + searchText + "%")
//                )
//        );
//
//        // Apply the search predicate
//        cq.where(predicate);
//
//        // Set pagination parameters
//        TypedQuery<Property> typedQuery = entityManager.createQuery(cq);
//        typedQuery.setFirstResult(pageNumber * pageSize); // Offset for pagination
//        typedQuery.setMaxResults(pageSize); // Limit the number of results per page
//
//        // Get the total count of matching results for pagination
//        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
//        Root<Property> countRoot = countQuery.from(Property.class);
//        countQuery.select(cb.count(countRoot)).where(predicate);
//        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();
//
//        // Fetch the results
//        List<Property> properties = typedQuery.getResultList();
//
//        // Return a Page object containing the results and pagination info
//        return new PageImpl<>(properties, PageRequest.of(pageNumber, pageSize), totalCount);
//    }



//    public Page<Property> getPropertiesByFilters(int page, int size, Map<String, String> filters) {
//        // Build the filtering criteria dynamically
//        Specification<Property> spec = PropertySpecifications.buildFilters(filters);
//        return propertyRepository.findAll(spec, PageRequest.of(page, size));
//    }
//
//    public Page<Property> getPropertiesByCategoryAndFilters(int page, int size, String category, Map<String, String> filters) {
//        Specification<Property> specification = Specification.where(null);
//
//        // Add category filter
//        if (category != null && !category.isEmpty()) {
//            specification = specification.and((root, query, criteriaBuilder) ->
//                    criteriaBuilder.equal(root.get("propertyType"), category));
//        }
//
//        // Add filters for offer type
//        if (filters.containsKey("offerTypeSale") || filters.containsKey("offerTypeRent")) {
//            Specification<Property> offerTypeSpecification = Specification.where(null);
//
//            if (filters.containsKey("offerTypeSale")) {
//                boolean includeSale = Boolean.parseBoolean(filters.get("offerTypeSale"));
//                if (includeSale) {
//                    offerTypeSpecification = offerTypeSpecification.or((root, query, criteriaBuilder) ->
//                            criteriaBuilder.equal(root.get("offerType"), "For sale"));
//                }
//            }
//
//            if (filters.containsKey("offerTypeRent")) {
//                boolean includeRent = Boolean.parseBoolean(filters.get("offerTypeRent"));
//                if (includeRent) {
//                    offerTypeSpecification = offerTypeSpecification.or((root, query, criteriaBuilder) ->
//                            criteriaBuilder.equal(root.get("offerType"), "For rent"));
//                }
//            }
//
//            // Combine the offer type specification with the main specification
//            specification = specification.and(offerTypeSpecification);
//        }
//
//        // Add price range filters
//        if (filters.containsKey("minPrice")) {
//            specification = specification.and((root, query, criteriaBuilder) ->
//                    criteriaBuilder.greaterThanOrEqualTo(root.get("salePrice"), Double.valueOf(filters.get("minPrice"))));
//        }
//        if (filters.containsKey("maxPrice")) {
//            specification = specification.and((root, query, criteriaBuilder) ->
//                    criteriaBuilder.lessThanOrEqualTo(root.get("salePrice"), Double.valueOf(filters.get("maxPrice"))));
//        }
//
//        return propertyRepository.findAll(specification, PageRequest.of(page, size));
//    }

//    public Page<Property> getPropertiesByCategoryAndFilters(int page, int size, String category, Map<String, String> filters) {
//        // Extract filter parameters
//        boolean includeSale = filters.containsKey("offerTypeSale") && Boolean.parseBoolean(filters.get("offerTypeSale"));
//        boolean includeRent = filters.containsKey("offerTypeRent") && Boolean.parseBoolean(filters.get("offerTypeRent"));
//        BigDecimal minPrice = filters.containsKey("minPrice") ? BigDecimal.valueOf(Double.parseDouble(filters.get("minPrice"))) : null;
//        BigDecimal maxPrice = filters.containsKey("maxPrice") ? BigDecimal.valueOf(Double.parseDouble(filters.get("maxPrice"))) : null;
//
//        // Fetch properties with the repository method
//        Page<Property> objectPage = propertyRepository.findPropertiesByFilters(
//                category,
//                includeSale,
//                includeRent,
//                minPrice,
//                maxPrice,
//                PageRequest.of(page, size)
//        );
//
//        if (objectPage == null || objectPage.getContent().isEmpty()) {
//            System.out.println("No results found");
//        } else {
//            objectPage.getContent().forEach(row -> {
//                System.out.println(row.getPropertyTitle()); // Log each row to check if data is returned
//            });
//        }

//        // Manually map Object[] to Property
//        List<Property> properties = objectPage.getContent().stream().map(row -> {
//            Property property = new Apartment();
//            property.setPropertyId((Long) row[0]); // Map each column to the corresponding Property field
//            property.setPropertyType((String) row[1]);
//            property.setOfferType((String) row[2]);
//            property.setSalePrice((BigDecimal) row[3]);
//            property.setRentPrice((BigDecimal) row[4]);
//            property.setStreet((String) row[5]);
//            property.setCity((String) row[6]);
//            property.setState((String) row[7]);
//            property.setPostalCode((String) row[8]);
//            property.setCountry((String) row[9]);
//            property.setPropertyTitle((String) row[10]);
//            property.setPropertyDescription((String) row[11]);
//            return property;
//        }).collect(Collectors.toList());

        // Return the properties as a page
//        return new PageImpl<>(properties, PageRequest.of(page, size), objectPage.getTotalElements());
//        return objectPage;
//    }



//    public Page<Property> getPropertiesByFilters(int page, int size, Map<String, String> filters) {
//        return getPropertiesByCategoryAndFilters(page, size, null, filters);
//    }
}