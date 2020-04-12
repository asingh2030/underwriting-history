package com.uw.model;

import com.uw.util.DocumentType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

@ApiModel("Documents involved in underwriting application.")
public class DocumentModel implements Serializable {
    @ApiModelProperty(notes="Document file name.")
    private String documentName;
    @ApiModelProperty(notes="Document file type.", allowableValues = "pdf/doc")
    private DocumentType documentType;

    public DocumentModel(){
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
