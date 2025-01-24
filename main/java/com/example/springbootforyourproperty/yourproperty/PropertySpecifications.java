package com.example.springbootforyourproperty.yourproperty;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropertySpecifications {
    public static Specification<Property> buildFilters(Map<String, String> filters) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                if (key.equals("OfferType")) {
                    predicates.add(criteriaBuilder.equal(root.get("offerType"), value));
                } else if (key.equals("propertyType")) {
                    predicates.add(criteriaBuilder.equal(root.get("propertyType"), value));
                }
                // Add more filtering options here as needed
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
