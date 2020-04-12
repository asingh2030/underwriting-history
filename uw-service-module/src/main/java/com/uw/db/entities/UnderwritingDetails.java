package com.uw.db.entities;

import com.uw.util.UWStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "UW_DETAILS")
public class UnderwritingDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Column(nullable = false)
    private Long appId;
    @Column(nullable = false)
    private UWStatus status;
    @Column(nullable = false)
    private String underwriterName;
    @Column(nullable = false)
    private int rulesetVersion;
    private String failedRulesIds;
    private int score;
    private Date createdDate;
    private Date modifiedDate;

    public UnderwritingDetails(){
        this.createdDate = new Date();
        this.modifiedDate = new Date();
    }
    public String getFailedRulesIds() {
        return failedRulesIds;
    }

    public void setFailedRulesIds(String failedRulesIds) {
        this.failedRulesIds = failedRulesIds;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UWStatus getStatus() {
        return status;
    }

    public void setStatus(UWStatus status) {
        this.status = status;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
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

    public String getUnderwriterName() {
        return underwriterName;
    }

    public void setUnderwriterName(String underwriterName) {
        this.underwriterName = underwriterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnderwritingDetails that = (UnderwritingDetails) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
