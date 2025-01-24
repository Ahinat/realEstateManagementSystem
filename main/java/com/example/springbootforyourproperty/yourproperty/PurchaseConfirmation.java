package com.example.springbootforyourproperty.yourproperty;

import com.example.springbootforyourproperty.yourpropertyuser.YourPropertyUser;
import jakarta.persistence.*;

@Entity
public class PurchaseConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long confirmationId;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private YourPropertyUser seller;

    @ManyToOne
    @JoinColumn(name = "purchaser_user_id")
    private YourPropertyUser purchaser;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(nullable = false)
    private Boolean confirmed = false;

    @Column(nullable = false)
    private Boolean forSale = true; // true: sale, false: rent

    public Long getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(Long confirmationId) {
        this.confirmationId = confirmationId;
    }

    public YourPropertyUser getSeller() {
        return seller;
    }

    public void setSeller(YourPropertyUser seller) {
        this.seller = seller;
    }

    public YourPropertyUser getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(YourPropertyUser purchaser) {
        this.purchaser = purchaser;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }
}
