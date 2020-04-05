package com.uw.db.model;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Entity
@Table(name = "DOCUMENT")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String documentName;
    private File documentDetails;//TODO: use as file, write file to blob and vise-versa converter
    private UnderwritingDetails uwDetails;

    public UnderwritingDetails getUwDetails() {
        return uwDetails;
    }

    public void setUwDetails(UnderwritingDetails uwDetails) {
        this.uwDetails = uwDetails;
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
}
