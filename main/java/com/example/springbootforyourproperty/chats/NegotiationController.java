package com.example.springbootforyourproperty.chats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/negotiations")
public class NegotiationController {

    @Autowired
    private NegotiationService negotiationService;

    @PostMapping("/offer")
    public ResponseEntity<?> offerNegotiation(@RequestBody String negotiationOffer) {
        return ResponseEntity.ok(negotiationService.offerNegotiation(negotiationOffer));
    }

    @GetMapping("/buyerOrRenter")
    public ResponseEntity<List<Negotiation>> getNegotiationsForBuyerOrRenter(
            @RequestParam Long buyerOrRenterId,
            @RequestParam Long propertyId) {
        List<Negotiation> negotiations = negotiationService.getNegotiationsForBuyerOrRenter(buyerOrRenterId, propertyId);
        return ResponseEntity.ok(negotiations);
    }

    @GetMapping("/seller")
    public ResponseEntity<List<Negotiation>> getNegotiationsForSeller(
            @RequestParam Long sellerId,
            @RequestParam Long propertyId) {
        List<Negotiation> negotiations = negotiationService.getNegotiationsForSeller(sellerId, propertyId);
        return ResponseEntity.ok(negotiations);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateNegotiationStatus(@RequestBody String updateStatus) {
        Negotiation.Status status =  negotiationService.updateNegotiationStatus(updateStatus);
        return ResponseEntity.ok("Negotiation status updated to " + status + " successfully.");
    }
}

