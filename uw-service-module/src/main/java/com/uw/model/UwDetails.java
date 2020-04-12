package com.uw.model;

import com.uw.db.entities.Underwriter;
import com.uw.util.UWStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@ApiModel(description = "Contains application details.")
public class UwDetails {
    @ApiModelProperty(notes="Underwriter name.")
    private String underwriterNames;
    @ApiModelProperty(notes="Underwriting status.")
    private String underwritingStatus;
    @ApiModelProperty(notes="Underwriting description.")
    private String description;
    @ApiModelProperty(notes="underiting id.")
    private Long underwritingId;
    @ApiModelProperty(notes="Underwriting verified date.")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    @ApiModelProperty(notes="Ruleset version.")
    private int rulesetVersion;
    @ApiModelProperty(notes="Underwriting score.")
    private int underwritingScore;
    @ApiModelProperty(notes="Documents used.")
    private String documents;
    @ApiModelProperty(notes="Underwriter business details.")
    private String businessDetails;
    @ApiModelProperty(notes="Underwriter business type.")
    private String businessType;
    @ApiModelProperty(notes="Underwriter business category.")
    private String businessCategory;
    @ApiModelProperty(notes="Underwriter business name.")
    private String businessName;
    @ApiModelProperty(notes="Underwriter business account.")
    private String businessAcc;
    @ApiModelProperty(notes="Underwriter business tax ID.")
    private String taxId;
    @ApiModelProperty(notes="Underwriter business address.")
    private String businessAddress;
    @ApiModelProperty(notes="Underwriter business channel.")
    private String underwriterChannel;

    public UwDetails(UWStatus underwritingStatus, String description, Long underwritingId, Date date, int rulesetVersion, int underwritingScore, String documents, Underwriter underwriter) {
        this.underwriterNames = underwriter.getBusinessName();
        this.underwritingStatus = underwritingStatus.name();
        this.description = description;
        this.underwritingId = underwritingId;
        this.date = date;
        this.rulesetVersion = rulesetVersion;
        this.underwritingScore = underwritingScore;
        this.documents = documents;
        this.businessDetails = underwriter.getBusinessDetails();
        this.businessType = underwriter.getBusinessType();
        this.businessCategory = underwriter.getBusinessCategory();
        this.businessName = underwriter.getBusinessName();
        this.businessAcc = underwriter.getBusinessAcc();
        this.taxId = underwriter.getTaxId();
        this.businessAddress = underwriter.getBusinessAddress();
        this.underwriterChannel = underwriter.getUnderwriterChannel();
    }

    public String getUnderwriterNames() {
        return underwriterNames;
    }

    public String getUnderwritingStatus() {
        return underwritingStatus;
    }

    public String getDescription() {
        return description;
    }

    public Long getUnderwritingId() {
        return underwritingId;
    }

    public Date getDate() {
        return date;
    }

    public int getRulesetVersion() {
        return rulesetVersion;
    }

    public int getUnderwritingScore() {
        return underwritingScore;
    }

    public String getDocuments() {
        return documents;
    }

    public String getBusinessDetails() {
        return businessDetails;
    }

    public String getBusinessType() {
        return businessType;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessAcc() {
        return businessAcc;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public String getUnderwriterChannel() {
        return underwriterChannel;
    }
}
