package com.uw.model;

import com.uw.util.UWStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("Underwriting details.")
public class UnderwritingDetailsModel implements Serializable {
    @ApiModelProperty("Underwriting created date.")
    private Long appId;
    @ApiModelProperty("Underwriting short description like reason of rejection.")
    private String description;
    @ApiModelProperty("Underwriting created date.")
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdDate;
    @ApiModelProperty("Underwriting last modified date.")
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date modifiedDate;
    @ApiModelProperty(value = "Underwriting status", allowableValues = "INIT, IN_PROGRESS, ON_HOLD, FAILED, COMPLETED")
    private UWStatus status;
    @ApiModelProperty("Underwriting ruleset version.")
    private int rulesetVersion;
    @ApiModelProperty(value = "Customer underwriting score.", example = "Like CIBIL score in banking.")
    private int score;
    @ApiModelProperty(value = "List of all failed rules while performing underwriting of associated ruleset version.")
    private List<String> failedRules;
    @ApiModelProperty(value = "Contains underwriter name.")
    private String underwriterName;
    @ApiModelProperty(value = "List of all failed rules while performing underwriting of associated ruleset version.")
    private List<DocumentModel> documents;



    public List<String> getFailedRules() {
        return failedRules;
    }

    public void setFailedRules(List<String> failedRules) {
        this.failedRules = failedRules;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UWStatus getStatus() {
        return status;
    }

    public void setStatus(UWStatus status) {
        this.status = status;
    }

    public int getRulesetVersion() {
        return rulesetVersion;
    }

    public void setRulesetVersion(int rulesetVersion) {
        this.rulesetVersion = rulesetVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DocumentModel> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentModel> documents) {
        this.documents = documents;
    }

    public String getUnderwriterName() {
        return underwriterName;
    }

    public void setUnderwriterName(String underwriterName) {
        this.underwriterName = underwriterName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
