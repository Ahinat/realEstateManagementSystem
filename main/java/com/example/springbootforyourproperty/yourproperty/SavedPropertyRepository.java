package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedPropertyRepository extends JpaRepository<SavedProperty, Long> {
    List<SavedProperty> findByYourPropertyUser(YourPropertyUser yourPropertyUser);
    Optional<SavedProperty> findByYourPropertyUserAndProperty(YourPropertyUser yourPropertyUser, Property property);
}