package com.example.springbootforyourproperty.yourproperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/saved-properties")
public class SavedPropertyController {
    @Autowired
    private SavedPropertyService savedPropertyService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProperty(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String title = request.get("title");
        String address = request.get("address");

        if (email == null || title == null || address == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        SavedProperty savedProperty = savedPropertyService.saveProperty(email, title, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProperty);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Map<String, Object>>> getProperty(@PathVariable String email) {
//        System.out.println("User with email " + email + " requested for saved properties");
        List<Map<String, Object>> savedProperties = savedPropertyService.getSavedProperties(email);
        return ResponseEntity.ok(savedProperties);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProperty(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String title = request.get("title");
        String address = request.get("address");

        if (email == null || title == null || address == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        boolean isDeleted = savedPropertyService.deleteSavedProperty(email, title, address);
        if (isDeleted) {
            return ResponseEntity.ok("Property deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
        }
    }
}
