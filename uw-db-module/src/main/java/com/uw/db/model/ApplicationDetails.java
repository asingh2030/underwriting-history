package com.uw.db.model;

import com.uw.db.util.ApplicationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "APP_DETAILS")
public class ApplicationDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerId;
    private LocalDateTime createdTime;
    private String createdBy;
    //TODO: move applicaiton modification details to another class and nade relation between them
    private LocalDateTime modifiedTime;
    private String modifiedBy;
    private ApplicationStatus status;

    protected ApplicationDetails(){
    }

    public ApplicationDetails(Long id, String customerId, String createdBy) {
        this.id = id;
        this.customerId = customerId;
        this.createdTime = LocalDateTime.now();
        this.createdBy = createdBy;
        this.status = ApplicationStatus.INIT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customerId;
    }

    public void setCustomer(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, createdTime);
    }
}
