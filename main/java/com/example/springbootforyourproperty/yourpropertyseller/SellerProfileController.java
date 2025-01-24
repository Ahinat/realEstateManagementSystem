package com.example.springbootforyourproperty.yourpropertyseller;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/yourpropertyseller")
public class SellerProfileController {
    @Autowired
    private SellerProfileService sellerProfileService;

    @Autowired
    private SellerProfileRepository sellerProfileRepository;

    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/register")
    public ResponseEntity<String> createBuyerProfile(@RequestBody String sellerProfile) throws JsonProcessingException {
        try {
            // Convert JSON string to JsonNode
            JsonNode sellerData = mapper.readTree(sellerProfile);

            sellerProfileService.createSellerProfile(sellerData);
            return ResponseEntity.status(HttpStatus.OK).body("Your seller profile has been created");
        } catch (Exception e) {
            throw new RuntimeException("Error creating renterProfile", e);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<SellerProfile> getSellerProfileByEmail(@PathVariable String email) {
        // Find the user in the main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + email + " not found");
        }

        try {
            // Retrieve the renter profile by email using the service
            SellerProfile sellerProfile = sellerProfileService.getSellerProfileByEmail(email);

            if (sellerProfile != null) {
                // Profile found, return it with 200 OK status
                return ResponseEntity.ok(sellerProfile);
            } else {
                // Profile not found, return 404 NOT FOUND status
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (RuntimeException e) {
            // Handle case where the user is not found or other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
