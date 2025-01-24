package com.example.springbootforyourproperty.yourproperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PurchasePaymentController {
    @Autowired
    private PurchasePaymentService purchasePaymentService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody String paymentData) {
        PurchasePayment payment = purchasePaymentService.addPurchasePayment(paymentData);
        return ResponseEntity.ok("Payment completed successfully");
    }

    @GetMapping("/rented/{email}")
    public ResponseEntity<?> getRentedProperties(@PathVariable String email) {
        List<Map<String, Object>> properties = purchasePaymentService.getRentedProperties(email);
        if(properties.isEmpty()) return ResponseEntity.ok(null);
        else return ResponseEntity.ok(properties);
    }

    @GetMapping("/rental/{email}")
    public ResponseEntity<?> getRentalProperties(@PathVariable String email) {
        List<Map<String, Object>> properties = purchasePaymentService.getRentalProperties(email);
        if(properties.isEmpty()) return ResponseEntity.ok(null);
        else return ResponseEntity.ok(properties);
    }
}
