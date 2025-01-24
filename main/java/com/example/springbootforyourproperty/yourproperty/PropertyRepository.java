package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyseller.SellerProfile;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> , JpaSpecificationExecutor<Property> {
    List<Property> findBySellerProfile(SellerProfile sellerProfile);

    @Query("SELECT p FROM Property p WHERE p.sellerProfile.yourPropertyUser.email = :email AND p.propertyTitle = :title AND CONCAT(p.street, ', ', p.city, ', ', p.state, ', ', p.postalCode, ', ', p.country) = :address")
    Optional<Property> findByEmailAndTitleAndAddress(@Param("email") String email, @Param("title") String title, @Param("address") String address);

    @Query("SELECT p FROM Property p WHERE p.propertyTitle = :title AND CONCAT(p.street, ', ', p.city, ', ', p.state, ', ', p.postalCode, ', ', p.country) = :address")
    Optional<Property> findByTitleAndAddress(@Param("title") String title, @Param("address") String address);

    Page<Property> findAll(Pageable pageable);
    Page<Property> findByPropertyType(String propertyType, Pageable pageable); // Property type only
    Page<Property> findByOfferType(String offerType, Pageable pageable); // Offer type only
    Page<Property> findByPropertyTypeAndOfferType(String propertyType, String offerType, Pageable pageable); // Property and offer type

    Page<Property> findBySalePriceGreaterThanEqualAndRentPriceIsNullOrSalePriceIsNullAndRentPriceGreaterThanEqual(BigDecimal minSalePrice, BigDecimal minRentPrice, Pageable pageable);
    Page<Property> findBySalePriceLessThanEqualAndRentPriceIsNullOrSalePriceIsNullAndRentPriceLessThanEqual(BigDecimal maxSalePrice, BigDecimal maxRentPrice, Pageable pageable);
    Page<Property> findBySalePriceBetweenAndRentPriceIsNullOrSalePriceIsNullAndRentPriceBetween(BigDecimal minSalePrice, BigDecimal maxSalePrice, BigDecimal minRentPrice, BigDecimal maxRentPrice, Pageable pageable);

    Page<Property> findByPropertyTypeAndSalePriceGreaterThanEqualAndRentPriceIsNullOrSalePriceIsNullAndRentPriceGreaterThanEqual(String propertyType, BigDecimal minSalePrice, BigDecimal minRentPrice, Pageable pageable);
    Page<Property> findByPropertyTypeAndSalePriceLessThanEqualAndRentPriceIsNullOrSalePriceIsNullAndRentPriceLessThanEqual(String propertyType, BigDecimal maxSalePrice, BigDecimal maxRentPrice, Pageable pageable);
    Page<Property> findByPropertyTypeAndSalePriceBetweenAndRentPriceIsNullOrSalePriceIsNullAndRentPriceBetween(String propertyType, BigDecimal minSalePrice, BigDecimal maxSalePrice, BigDecimal minRentPrice, BigDecimal maxRentPrice, Pageable pageable);

    Page<Property> findByPropertyTypeAndOfferTypeAndSalePriceGreaterThanEqual(String propertyType, String offerType, BigDecimal minSalePrice, Pageable pageable);
    Page<Property> findByPropertyTypeAndOfferTypeAndSalePriceLessThanEqual(String propertyType, String offerType, BigDecimal maxSalePrice, Pageable pageable);
    Page<Property> findByPropertyTypeAndOfferTypeAndSalePriceBetween(String propertyType, String offerType, BigDecimal minSalePrice, BigDecimal maxSalePrice, Pageable pageable);

    Page<Property> findByPropertyTypeAndOfferTypeAndRentPriceGreaterThanEqual(String propertyType, String offerType, BigDecimal minRentPrice, Pageable pageable);
    Page<Property> findByPropertyTypeAndOfferTypeAndRentPriceLessThanEqual(String propertyType, String offerType, BigDecimal maxRentPrice, Pageable pageable);
    Page<Property> findByPropertyTypeAndOfferTypeAndRentPriceBetween(String propertyType, String offerType, BigDecimal minRentPrice, BigDecimal maxRentPrice, Pageable pageable);

    Page<Property> findByOfferTypeAndSalePriceGreaterThanEqual(String offerType, BigDecimal minSalePrice, Pageable pageable);
    Page<Property> findByOfferTypeAndSalePriceLessThanEqual(String offerType, BigDecimal maxSalePrice, Pageable pageable);
    Page<Property> findByOfferTypeAndSalePriceBetween(String offerType, BigDecimal minSalePrice, BigDecimal maxSalePrice, Pageable pageable);

    Page<Property> findByOfferTypeAndRentPriceGreaterThanEqual(String offerType, BigDecimal minRentPrice, Pageable pageable);
    Page<Property> findByOfferTypeAndRentPriceLessThanEqual(String offerType, BigDecimal maxRentPrice, Pageable pageable);
    Page<Property> findByOfferTypeAndRentPriceBetween(String offerType, BigDecimal minRentPrice, BigDecimal maxRentPrice, Pageable pageable);


}