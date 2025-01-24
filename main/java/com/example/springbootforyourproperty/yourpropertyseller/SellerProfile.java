package com.example.springbootforyourproperty.yourpropertyseller;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class SellerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private YourPropertyUser yourPropertyUser;

    private String sellerLicense;

    @ElementCollection
    @CollectionTable(name = "seller_owned_property_type", joinColumns = @JoinColumn(name = "seller_id"))
    private List<String> sellerOwnedPropertyType;

    @ElementCollection
    @CollectionTable(name = "seller_opened_communication_usertype", joinColumns = @JoinColumn(name = "seler_id"))
    private List<String> sellerOpenedCommunictionUserType;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public YourPropertyUser getYourPropertyUser() {
        return yourPropertyUser;
    }

    public void setYourPropertyUser(YourPropertyUser yourPropertyUser) {
        this.yourPropertyUser = yourPropertyUser;
    }

    public String getSellerLicense() {
        return sellerLicense;
    }

    public void setSellerLicense(String sellerLicense) {
        this.sellerLicense = sellerLicense;
    }

    public List<String> getSellerOwnedPropertyType() {
        return sellerOwnedPropertyType;
    }

    public void setSellerOwnedPropertyType(List<String> sellerOwnedPropertyType) {
        this.sellerOwnedPropertyType = sellerOwnedPropertyType;
    }

    public List<String> getSellerOpenedCommunictionUserType() {
        return sellerOpenedCommunictionUserType;
    }

    public void setSellerOpenedCommunictionUserType(List<String> sellerOpenedCommunictionUserType) {
        this.sellerOpenedCommunictionUserType = sellerOpenedCommunictionUserType;
    }
}
