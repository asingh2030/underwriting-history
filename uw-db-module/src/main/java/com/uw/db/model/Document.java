package com.uw.db.model;

import com.uw.db.util.DocumentType;

import javax.persistence.*;
import java.io.File;
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
    private File documentDetails;//TODO: use as file, write file to blob and vise-versa converter
    private Long applicationId;

    public Document(Long id, String documentName, DocumentType documentType, File documentDetails, Long applicationId) {
        this.id = id;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentDetails = documentDetails;
        this.applicationId = applicationId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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

    public File getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(File documentDetails) {
        this.documentDetails = documentDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(getDocumentName(), document.getDocumentName()) &&
                getDocumentType() == document.getDocumentType() &&
                Objects.equals(getApplicationId(), document.getApplicationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumentName(), getDocumentType(), getApplicationId());
    }
}
