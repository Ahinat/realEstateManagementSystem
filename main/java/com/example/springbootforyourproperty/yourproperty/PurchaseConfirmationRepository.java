package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseConfirmationRepository extends JpaRepository<PurchaseConfirmation, Integer> {
    Optional<PurchaseConfirmation> findPurchaseConfirmationByPurchaserAndSellerAndProperty(YourPropertyUser purchaser, YourPropertyUser seller, Property property);
}
