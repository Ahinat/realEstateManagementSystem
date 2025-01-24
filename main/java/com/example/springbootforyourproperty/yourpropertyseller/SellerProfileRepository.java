package com.example.springbootforyourproperty.yourpropertyseller;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerProfileRepository extends JpaRepository<SellerProfile, Long> {
    SellerProfile findByYourPropertyUser(YourPropertyUser yourPropertyUser);
}