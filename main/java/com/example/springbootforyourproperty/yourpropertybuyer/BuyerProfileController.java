package com.example.springbootforyourproperty.yourpropertybuyer;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/yourpropertybuyer")
public class BuyerProfileController {

    @Autowired
    private BuyerProfileService buyerProfileService;
    @Autowired
    private BuyerProfileRepository buyerProfileRepository;
    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    @PostMapping("/register")
    public ResponseEntity<String> createBuyerProfile(@RequestBody BuyerProfileRequest buyerProfileRequest) {
        // Log email to verify it's being received
        System.out.println("Received email: " + buyerProfileRequest.getEmail());

        buyerProfileService.createBuyerProfile(buyerProfileRequest.getEmail(), buyerProfileRequest.getMinBudget(), buyerProfileRequest.getMaxBudget(), buyerProfileRequest.getPropertyTypes(), buyerProfileRequest.getLocations());
        return ResponseEntity.status(HttpStatus.OK).body("Your buyer profile has been created");
    }

//    @GetMapping("/{id}")
//    public BuyerProfile getBuyer(@PathVariable Long id) {
//        return buyerProfileService.getBuyer(id);
//    }

    // @GetMapping("/buyer")
    // public ResponseEntity<BuyerProfile> getUserProfileDetails(@RequestParam String email) {
    //     // Find the user in main table
    //     YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);

    //     if (yourPropertyUser == null) {
    //         // If user does not exist, throw an error or handle it
    //         throw new RuntimeException("User with email " + email + " not found");
    //     }

    //     BuyerProfile buyer = buyerProfileRepository.findByYourPropertyUser(yourPropertyUser);
    //     if (buyer == null) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     }
    //     return ResponseEntity.ok(buyer);
    // }

//    @GetMapping("/{email}")
//    public ResponseEntity<BuyerProfile> getBuyerProfileByEmail(@PathVariable String email) {
//        // Find the user in the main table
//        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);
//
//        if (yourPropertyUser == null) {
//            // If user does not exist, throw an error or handle it
//            throw new RuntimeException("User with email " + email + " not found");
//        }
//
//        BuyerProfile buyer = buyerProfileRepository.findByYourPropertyUser(yourPropertyUser);
//        if (buyer == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//        return ResponseEntity.ok(buyer);
//    }

    @GetMapping("/{email}")
    public ResponseEntity<BuyerProfile> getBuyerProfileByEmail(@PathVariable String email) {
        // Find the user in the main table
        YourPropertyUser yourPropertyUser = yourPropertyUserRepository.findByEmail(email);

        if (yourPropertyUser == null) {
            // If user does not exist, throw an error or handle it
            throw new RuntimeException("User with email " + email + " not found");
        }

        try {
            // Retrieve the renter profile by email using the service
            BuyerProfile buyerProfile = buyerProfileService.getBuyerProfileByEmail(email);

            if (buyerProfile != null) {
                // Profile found, return it with 200 OK status
                return ResponseEntity.ok(buyerProfile);
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
