package com.uw.db.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UNDERWRITER")
public class Underwriter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String businessDetails;
    private String businessType;
    private String businessCategory;
    private String businessName;
    private String businessAcc;
    private String taxId;
    private String businessAddress;
    private String underwriterChannel;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(String businessDetails) {
        this.businessDetails = businessDetails;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAcc() {
        return businessAcc;
    }

    public void setBusinessAcc(String businessAcc) {
        this.businessAcc = businessAcc;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getUnderwriterChannel() {
        return underwriterChannel;
    }

    public void setUnderwriterChannel(String underwriterChannel) {
        this.underwriterChannel = underwriterChannel;
    }

}
