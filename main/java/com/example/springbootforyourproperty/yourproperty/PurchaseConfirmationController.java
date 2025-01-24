package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.chats.Negotiation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/purchase-confirmation")
public class PurchaseConfirmationController {
    @Autowired
    private PurchaseConfirmationService purchaseConfirmationService;

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestBody String jsonBody) {
        purchaseConfirmationService.updateConfirmation(jsonBody);
        return ResponseEntity.ok("Confirmation updated successfully.");
    }

    @PostMapping("/get")
    public ResponseEntity<String> getConfirmation(@RequestBody String jsonBody) {
        Optional<PurchaseConfirmation> purchaseConfirmation = purchaseConfirmationService.getConfirmation(jsonBody);
        if(purchaseConfirmation.isPresent())
            return ResponseEntity.ok(purchaseConfirmation.get().getConfirmed().toString());
        else
            return ResponseEntity.ok(null);
    }
}
