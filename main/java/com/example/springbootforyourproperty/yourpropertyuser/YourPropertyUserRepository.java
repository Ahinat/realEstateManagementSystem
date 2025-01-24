package com.example.springbootforyourproperty.yourpropertyuser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface YourPropertyUserRepository extends JpaRepository<YourPropertyUser, Long> {
    YourPropertyUser findByEmail(String email);
}

