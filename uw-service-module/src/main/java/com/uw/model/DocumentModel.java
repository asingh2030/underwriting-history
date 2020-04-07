package com.uw.model;

import com.uw.db.util.DocumentType;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;

public class DocumentModel implements Serializable {
    private Long id;
    private String documentName;
    private DocumentType documentType;
//    private File documentDetails;//TODO: use as file, write file to blob and vise-versa converter
    private Long applicationId;
    private Long uwId;
    private String customerId;

    public DocumentModel(){
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
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
