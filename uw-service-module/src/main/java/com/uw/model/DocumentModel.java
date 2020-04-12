package com.uw.model;

import com.uw.util.DocumentType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

@ApiModel("Documents involved in underwriting application.")
public class DocumentModel implements Serializable {
    @ApiModelProperty(notes="Document unique indentifier.")
    private Long id;
    @ApiModelProperty(notes="Document file name.")
    private String documentName;
    @ApiModelProperty(notes="Document file type.", allowableValues = "pdf/doc")
    private DocumentType documentType;
//    private File documentDetails;//TODO: use as file, write file to blob and vise-versa converter
    @ApiModelProperty(notes="Document belongs to application id.")
    private Long applicationId;
    @ApiModelProperty(notes="Document belongs to underwriting id.")
    private Long uwId;
    @ApiModelProperty(notes="Document belongs to customer id.")
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
