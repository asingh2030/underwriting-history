package com.uw.db.entities;

import com.uw.util.ApplicationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "APP_DETAILS")
public class ApplicationDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerId;
//    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private String createdBy;
//    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    private String modifiedBy;
    private ApplicationStatus status;
    private String customerAddress;
    @OneToMany(mappedBy = "appId", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Document> documentList;
    @OneToMany(mappedBy = "appId", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<UnderwritingDetails> uwList;

    public ApplicationDetails(){
        this.createdDate = new Date();
        this.modifiedDate = new Date();
        this.documentList = new HashSet<>();
        this.uwList = new HashSet<>();
    }

    public ApplicationDetails(String customerId, String createdBy) {
        this.customerId = customerId;
        this.createdDate = new Date();
        this.createdBy = createdBy;
    }
    public List<Document> getDocumentList() {
        return new ArrayList<>(documentList);
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList.addAll(documentList);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationDetails that = (ApplicationDetails) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<UnderwritingDetails> getUwList() {
        return new ArrayList<>(uwList);
    }

    public void setUwList(List<UnderwritingDetails> uwList) {
        this.uwList.addAll(uwList);
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @PrePersist
    public void prePersist(){
        Long appId = getId();
        System.out.println("App Pre persist call for app id "+appId);
        uwList.forEach(uw->uw.setAppId(appId));
        documentList.forEach(document->document.setAppId(appId));
    }


    public Date getCreatedDate() {
        return createdDate;
    }
}
