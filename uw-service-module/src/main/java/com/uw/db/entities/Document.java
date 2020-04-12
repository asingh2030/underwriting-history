package com.uw.db.entities;

import com.uw.util.DocumentType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DOCUMENT")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String documentName;
    private DocumentType documentType;
    private Long appId;
    private Long uwId;
    private String customerId;

    public Document(){
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Long getUwId() {
        return uwId;
    }

    public void setUwId(Long uwId) {
        this.uwId = uwId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(getDocumentName(), document.getDocumentName()) &&
                getDocumentType() == document.getDocumentType() &&
                Objects.equals(getAppId (), document.getAppId ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumentName(), getDocumentType(), getAppId ());
    }
}
