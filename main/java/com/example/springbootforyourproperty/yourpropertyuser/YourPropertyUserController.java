package com.example.springbootforyourproperty.yourpropertyuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/yourpropertyuser")
public class YourPropertyUserController {
    private static final Logger logger = LoggerFactory.getLogger(YourPropertyUserController.class);

    @Autowired
    private final YourPropertyUserService yourPropertyUserService;

    public YourPropertyUserController(YourPropertyUserService yourPropertyUserService) {
        this.yourPropertyUserService = yourPropertyUserService;
    }

    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody YourPropertyUser yourPropertyUser) {
        YourPropertyUser existingUser = yourPropertyUserRepository.findByEmail(yourPropertyUser.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        try{
            YourPropertyUser storedUser = yourPropertyUserService.storeUser(yourPropertyUser);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully: " + storedUser.getFirstName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while storing user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody YourPropertyUser yourPropertyUser) {
        YourPropertyUser existingUser = yourPropertyUserRepository.findByEmail(yourPropertyUser.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(yourPropertyUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body("User logged in successfully: " + yourPropertyUser.getFirstName());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

//    @GetMapping("/{email}")
//    public YourPropertyUser getUser(@PathVariable String email) {
//        return yourPropertyUserRepository.findByEmail(email);
//    }

    @GetMapping("/{email}")
    public ResponseEntity<YourPropertyUser> getUserProfileDetails(@PathVariable String email) {
        YourPropertyUser user = yourPropertyUserRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }


//    @PutMapping("/{email}/profiledetails")
//    public ResponseEntity<String> updateUserProfileDetails(@PathVariable String email, @RequestPart("userDetails") YourPropertyUser yourPropertyUser, @RequestPart(value = "file", required = false) MultipartFile file) {
//        try {
//            yourPropertyUserService.editUserProfileDetails(email, yourPropertyUser, file);
//            return ResponseEntity.status(HttpStatus.OK).body("User updated successfully.");
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user.");
//        }
//    }


    @PutMapping("/{email}/profiledetails")
    public ResponseEntity<String> updateUserProfileDetails(@PathVariable String email, @RequestBody YourPropertyUser yourPropertyUser) throws IOException {
        YourPropertyUser changeUser = yourPropertyUserService.editUserProfileDetails(email, yourPropertyUser);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully: " + changeUser.getFirstName());
    }

//    @PutMapping("/{email}/logindetails")
//    public ResponseEntity<String> updateUserLoginDetails(@PathVariable String email, @RequestBody YourPropertyUser yourPropertyUser) {
//        logger.info("Received request to update a user's login details: {}", yourPropertyUser.getFirstName());
//
//        YourPropertyUser changeUser = yourPropertyUserService.editUserLoginDetails(email, yourPropertyUser);
//        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully: " + changeUser.getFirstName());
//    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable Long userId) {
        boolean isDeleted = yourPropertyUserService.deleteUserById(userId);
        if (isDeleted) {
            return ResponseEntity.ok("User account deleted successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

//    @DeleteMapping("/{email}/deleteuser")
//    public ResponseEntity<String> deleteUser(@PathVariable String email) {
//        logger.info("Received request to delete a user: {}", email);
//
//        boolean isDeleted = YourPropertyUserService.deleteUserAccount(email);
//        if(isDeleted) {
//            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully: " + email);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting user: " + email);
//        }
//    }


    @PostMapping("/{email}/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable String email, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = yourPropertyUserService.saveProfilePicture(file, email);
            // Here you can also update the User entity with the new profile picture path in the database
            return ResponseEntity.ok("Profile picture uploaded: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

}


