package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyseller.SellerProfile;
import com.example.springbootforyourproperty.yourpropertyseller.SellerProfileService;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    @Autowired
    private SellerProfileService sellerProfileService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Endpoint to create a new property
    @PostMapping("/add")
    public ResponseEntity<Property> createProperty(@RequestBody String propertyData) {
        return ResponseEntity.ok(propertyService.addProperty(propertyData));
    }

    // Endpoint to add photos to an existing property
    @PostMapping("/{id}/photos")
    public ResponseEntity<Property> addPhotos(@PathVariable Long id, @RequestBody List<String> photoUrls) {
        return ResponseEntity.ok(propertyService.addPhotos(id, photoUrls));
    }

    // Endpoint to fetch a property by ID
    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    // Endpoint to fetch all properties
//    @GetMapping
//    public ResponseEntity<List<Property>> getAllProperties() {
//        return ResponseEntity.ok(propertyService.getAllProperties());
//    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getPropertyByEmail(@PathVariable String email) {
        // Find the user in the main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + email + " not found");
        }

        SellerProfile sellerProfile = sellerProfileService.getSellerProfileByEmail(email);
        if (sellerProfile == null) {
//            throw new RuntimeException("User with email " + email + " doesn't have a seller profile.");
            return ResponseEntity.ok(null);
        }

        List<Property> properties = propertyService.findPropertiesBySellerProfile(sellerProfile);
        return ResponseEntity.ok(properties);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProperty(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        String address = request.get("address");

        if (title == null || address == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        boolean isDeleted = propertyService.deleteProperty(title, address);
        if (isDeleted) {
            return ResponseEntity.ok("Property deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
        }
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteProperty(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//        String title = request.get("title");
//        String address = request.get("address");
//
//        if (email == null || title == null || address == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
//        }
//
//        boolean isDeleted = propertyService.deleteProperty(email, title, address);
//        if (isDeleted) {
//            return ResponseEntity.ok("Property deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
//        }
//    }

//    @GetMapping("/seller/{sellerId}")
//    public ResponseEntity<List<Property>> getPropertiesBySellerId(@PathVariable Long sellerId) {
//        return ResponseEntity.ok(propertyService.findPropertiesBySellerProfile(sellerId));
//    }

    @PostMapping("/property")
    public ResponseEntity<?> getPropertyByDetails(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String title = request.get("title");
        String address = request.get("address");

        if (email == null || title == null || address == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        Property property = propertyService.getProperty(title, address);
        if (property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
        } else {
            return ResponseEntity.ok(property);
        }
    }

    @PostMapping("/propertyseller")
    public ResponseEntity<?> getPropertySellerByPropertyDetails(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        String address = request.get("address");

        if (title == null || address == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        Map<String, Object> propertySeller = propertyService.getPropertySeller(title, address);
        if (propertySeller == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property seller not found");
        } else {
            return ResponseEntity.ok(propertySeller);
        }
    }

    @GetMapping
    public PaginatedResponse<Property> getProperties(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String offerType,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String sortBy) {

        Page<Property> propertyPage = null;

        Sort sort = Sort.unsorted();
        if (sortBy != null) {
            if (sortBy.equals("highToLow")) {
                sort = Sort.by(Sort.Order.desc("salePrice").ignoreCase(), Sort.Order.desc("rentPrice").ignoreCase());
            } else if (sortBy.equals("lowToHigh")) {
                sort = Sort.by(Sort.Order.asc("salePrice").ignoreCase(), Sort.Order.asc("rentPrice").ignoreCase());
            }
        }
        Pageable pageable = PageRequest.of(page, size, sort);

        // Check for category, offer type, and price range
        if (category != null && !category.isEmpty()) {
            if (offerType != null && !offerType.isEmpty()) {
                if (minPrice != null || maxPrice != null) {
                    // Category + Offer Type + Price Range
                    if ("For sale".equalsIgnoreCase(offerType)) {
                        propertyPage = propertyService.getPropertiesByCategoryAndOfferTypeAndSalePriceRange(pageable, category, offerType, minPrice, maxPrice);
                    } else if ("For rent".equalsIgnoreCase(offerType)) {
                        propertyPage = propertyService.getPropertiesByCategoryAndOfferTypeAndRentPriceRange(pageable, category, offerType, minPrice, maxPrice);
                    } else {
                        propertyPage = propertyService.getPropertiesByCategoryAndPriceRange(pageable, category, minPrice, maxPrice, minPrice, maxPrice);
                    }
                } else {
                    // Category + Offer Type
                    propertyPage = propertyService.getPropertiesByCategoryAndOfferType(pageable, category, offerType);
                }
            } else {
                if (minPrice != null || maxPrice != null) {
                    // Category + Price Range
                    propertyPage = propertyService.getPropertiesByCategoryAndPriceRange(pageable, category, minPrice, maxPrice, minPrice, maxPrice);
                } else {
                    // Category
                    propertyPage = propertyService.getPropertiesByCategory(pageable, category);
                }
            }
        } else if (offerType != null && !offerType.isEmpty()) {
            if (minPrice != null || maxPrice != null) {
                // Offer Type + Price Range
                if ("For sale".equalsIgnoreCase(offerType)) {
                    propertyPage = propertyService.getPropertiesByOfferTypeAndSalePriceRange(pageable, offerType, minPrice, maxPrice);
                } else if ("For rent".equalsIgnoreCase(offerType)) {
                    propertyPage = propertyService.getPropertiesByOfferTypeAndRentPriceRange(pageable, offerType, minPrice, maxPrice);
                }
            } else {
                // Offer Type
                propertyPage = propertyService.getPropertiesByOfferType(pageable, offerType);
            }
        } else {
            if (minPrice != null || maxPrice != null) {
                // Price Range
                propertyPage = propertyService.getPropertiesByPriceRange(pageable, minPrice, maxPrice, minPrice, maxPrice);
            } else {
                // Default case, return all properties
                propertyPage = propertyService.getProperties(pageable);
            }
        }

        return new PaginatedResponse<>(
                propertyPage.getContent(),
                propertyPage.getTotalPages(),
                propertyPage.getTotalElements(),
                propertyPage.getNumber()
        );
    }

//    @GetMapping
//    public PaginatedResponse<Property> getProperties(
//            @RequestParam int page,
//            @RequestParam int size,
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) Map<String, String> filters) {
//        Page<Property> propertyPage;
//
//        if (category != null && !category.isEmpty()) {
//            propertyPage = propertyService.getPropertiesByCategoryAndFilters(page, size, category, filters);
//        } else {
//            propertyPage = propertyService.getPropertiesByFilters(page, size, filters);
//        }
//
//        return new PaginatedResponse<>(
//                propertyPage.getContent(),
//                propertyPage.getTotalPages(),
//                propertyPage.getTotalElements(),
//                propertyPage.getNumber()
//        );
//    }

//    @GetMapping
//    public PaginatedResponse<Property> getProperties(
//            @RequestParam int page,
//            @RequestParam int size,
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) Map<String, String> filters) {
//
//        Page<Property> propertyPage;
//
//        if (category != null && !category.isEmpty()) {
//            propertyPage = propertyService.getPropertiesByCategoryAndFilters(page, size, category, filters);
//        } else {
//            propertyPage = propertyService.getPropertiesByFilters(page, size, filters);
//        }
//
//        return new PaginatedResponse<>(
//                propertyPage.getContent(),
//                propertyPage.getTotalPages(),
//                propertyPage.getTotalElements(),
//                propertyPage.getNumber()
//        );
//    }


    @PostMapping("/property/get")
    public Property getProperty(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        String address = request.get("address");
        return propertyService.getProperty(title, address);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> updateProperty(@RequestBody String propertyData) {
        Property updatedProperty = propertyService.updateProperty(propertyData);
        System.out.println(updatedProperty.getPicture());
        return ResponseEntity.status(HttpStatus.OK).body("User property successfully: " + updatedProperty.getPropertyTitle());
    }

    @PostMapping("/search")
    public List<Property> searchProperties(@RequestBody String keyword) {
        return propertyService.searchProperties(keyword);
    }

//    @GetMapping("/search")
//    public Page<Property> searchProperties(@RequestParam int page, @RequestParam int size, @RequestParam String keyword) {
//        return propertyService.searchProperties(keyword, page, size);
//    }
}
