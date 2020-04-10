package com.uw.service;

import com.uw.db.entities.Document;
import com.uw.db.repo.DocumentRepository;
import com.uw.model.DocumentModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository repository;

    public List<DocumentModel> getDocumentModels(List<Document> docList) {
        if (docList != null) {
            return docList.stream().map(DocumentService::mapToDocModel).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static DocumentModel mapToDocModel(Document document) {
        DocumentModel model = new DocumentModel();
        BeanUtils.copyProperties(document, model);
        return model;
    }

    public List<DocumentModel> getDocumentBySsn(String ssn){
        Assert.notNull(ssn,"Customer SSN must not be null.");
        List<Document> docList = repository.findByCustomerId(ssn);
        return getDocumentModels(docList);
    }

    public List<DocumentModel> getDocumentByUwId(Long uwId){
        Assert.notNull(uwId,"Underwriting Id must not be null.");
        List<Document> docList = repository.findByUwId(uwId);
        return getDocumentModels(docList);
    }
}
