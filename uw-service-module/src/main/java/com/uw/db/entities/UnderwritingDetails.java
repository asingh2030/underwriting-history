package com.uw.db.entities;

import com.uw.util.UWStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "UW_DETAILS")
public class UnderwritingDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Date createdDate;
    private Date modifiedDate;
    @JoinColumn(name = "id", nullable = false)
    private Long appId;
    private UWStatus status;
    private int rulesetVersion;
    private String failedRulesIds;
    private int score;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "businessName")
    private Underwriter underwriter;

    @OneToMany(mappedBy = "uwId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Document> documentsList;

    public UnderwritingDetails(){
        this.createdDate = new Date();
        this.modifiedDate = new Date();
        this.documentsList = new HashSet<>();
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

    public List<Document> getDocumentsList() {
        return new ArrayList<>(documentsList);
    }

    public void setDocumentsList(List<Document> documentsList) {
        this.documentsList.addAll(documentsList);
    }

    public Underwriter getUnderwriter() {
        return underwriter;
    }

    public void setUnderwriter(Underwriter underwriter) {
        this.underwriter = underwriter;
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

    @PrePersist
    public void prePersist(){
        Long uwId = getId();
        System.out.println("App Pre persist call for app id "+uwId);
        documentsList.forEach(document->document.setUwId(uwId));
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
