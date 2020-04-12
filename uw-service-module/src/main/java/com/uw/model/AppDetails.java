package com.uw.model;

import com.uw.util.ApplicationStatus;
import com.uw.util.UWStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@ApiModel(description = "Contains application details.")
public class AppDetails {

    @ApiModelProperty(notes="Customer application identifier.")
    private Long applicationId;
    @ApiModelProperty(notes="Application created date.")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    @ApiModelProperty(notes="Application status.")
    private ApplicationStatus status;
    @ApiModelProperty(notes="Application status description.")
    private String description;
    @ApiModelProperty(notes="Underwriter identifier.")
    private Long underwriterId;
    @ApiModelProperty(notes="Underwriter name.")
    private String underwriterName;
    @ApiModelProperty(notes="Underwriting verify Date.")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date verifyDate;
    @ApiModelProperty(notes="Underwriting status.")
    private UWStatus underwritingStatus;

    public AppDetails(Long applicationId, Date date, ApplicationStatus status, String description, Long underwriterId, String underwriterName, Date verifyDate, UWStatus underwritingStatus) {
        this.applicationId = applicationId;
        this.date = date;
        this.status = status;
        this.description = description;
        this.underwriterId = underwriterId;
        this.underwriterName = underwriterName;
        this.verifyDate = verifyDate;
        this.underwritingStatus = underwritingStatus;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public Date getDate() {
        return date;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Long getUnderwriterId() {
        return underwriterId;
    }

    public String getUnderwriterName() {
        return underwriterName;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public UWStatus getUnderwritingStatus() {
        return underwritingStatus;
    }
}
