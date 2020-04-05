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
    private ApplicationDetails appDetails;
    private UWStatus status;
    private boolean isHealth;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Document> documentsList;

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

    public ApplicationDetails getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(ApplicationDetails appDetails) {
        this.appDetails = appDetails;
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

    public List<Document> getDocumentsList() {
        return documentsList;
    }

    public void setDocumentsList(List<Document> documentsList) {
        this.documentsList = documentsList;
    }
}
