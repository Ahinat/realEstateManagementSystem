package com.example.springbootforyourproperty.yourpropertybuyer;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerProfileRepository extends JpaRepository<BuyerProfile, Long> {
    BuyerProfile findByYourPropertyUser(YourPropertyUser yourPropertyUser);
}
