package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasePaymentRepository extends JpaRepository<PurchasePayment, Integer> {
    List<PurchasePayment> findBySeller(YourPropertyUser seller);
    List<PurchasePayment> findByPurchaser(YourPropertyUser purchaser);
}
