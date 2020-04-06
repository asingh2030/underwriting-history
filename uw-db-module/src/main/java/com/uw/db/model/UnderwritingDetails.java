package com.uw.db.model;

import com.uw.db.util.UWStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "UW_DETAILS")
public class UnderwritingDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String underwriterName;
    private Long appId;
    private UWStatus status;
    private boolean isHealth;
    private int rulesetVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnderwriterName() {
        return underwriterName;
    }

    public void setUnderwriterName(String underwriterName) {
        this.underwriterName = underwriterName;
    }

    public UWStatus getStatus() {
        return status;
    }

    public void setStatus(UWStatus status) {
        this.status = status;
    }

    public boolean isHealth() {
        return isHealth;
    }

    public void setHealth(boolean health) {
        isHealth = health;
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
}
