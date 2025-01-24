package com.example.springbootforyourproperty.yourpropertyrenter;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RenterProfileRepository extends JpaRepository<RenterProfile, Long> {
    RenterProfile findByYourPropertyUser(YourPropertyUser yourPropertyUser);
}

