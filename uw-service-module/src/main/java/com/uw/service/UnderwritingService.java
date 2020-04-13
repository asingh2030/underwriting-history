package com.uw.service;

import com.uw.db.entities.Document;
import com.uw.db.entities.Underwriter;
import com.uw.db.entities.UnderwritingDetails;
import com.uw.db.repo.UnderwriterRepository;
import com.uw.db.repo.UnderwritingDetailsRepository;
import com.uw.exception.ResourceNotFoundException;
import com.uw.model.DocumentModel;
import com.uw.model.RuleModel;
import com.uw.model.UnderwritingDetailsModel;
import com.uw.model.UwDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnderwritingService {
    @Autowired
    private UnderwritingDetailsRepository repository;
    @Autowired
    private UnderwriterRepository underwriterRepository;
    @Autowired
    private DocumentService documentService;
    @Autowired
    RuleService ruleService;

    public UwDetails getUwDetailsByAppId(Long appId){
        Assert.notNull(appId, "Application Id is must to fetch underwriting details.");
        List<UnderwritingDetails> list = repository.findAllByAppId(appId);
        return getUwDetails(appId, list);
    }

    public UnderwritingDetailsModel getUwDetailsModelByAppId(Long appId){
        Assert.notNull(appId, "Application Id is must to fetch underwriting details.");
        List<UnderwritingDetails> list = repository.findAllByAppId(appId);
        return getUwDetailsModel(appId, list.get(0));
    }

    private UnderwritingDetailsModel getUwDetailsModel(Long appId, UnderwritingDetails entity) {
        UnderwritingDetailsModel model = new UnderwritingDetailsModel();
        BeanUtils.copyProperties(entity, model);
        model.setStatus(entity.getStatus());
        model.setDocuments(documentService.getDocumentByUwId(entity.getId()));
        return model;
    }

    private UwDetails getUwDetails(Long appId, List<UnderwritingDetails> list) {
        if(list == null || list.isEmpty()){
            throw new ResourceNotFoundException("Underwriting details not found belongs to given application id "+appId);
        }
        UnderwritingDetails underwritingDetails = list.stream().findAny().get();
        Long id = underwritingDetails.getId();
        Underwriter underwriter = getUnderwriter(underwritingDetails.getUnderwriterName());
        List<DocumentModel> documentList = documentService.getDocumentByUwId(id);
        String documents = "";
        if(documentList != null && !documentList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            documentList.forEach(doc->{
                sb.append("{ ");
                sb.append(doc.toString());
                sb.append(" }");
            });
            sb.append(" ]");
            documents = sb.toString();
        }
        UwDetails model = new UwDetails(underwritingDetails.getStatus(),underwritingDetails.getDescription(), id,
                underwritingDetails.getModifiedDate(),underwritingDetails.getRulesetVersion(),underwritingDetails.getScore(),documents,underwriter);
        return model;
    }

    public Underwriter getUnderwriter(String underwriterName) {
        return underwriterRepository.findOneByBusinessName(underwriterName);
    }

    public void save(UnderwritingDetailsModel uwDetailsModel, String ssn, Long appId) {
        Underwriter underwriter = getUnderwriter (uwDetailsModel.getUnderwriterName());
        validarteRuleset (uwDetailsModel);

        UnderwritingDetails uwDetails = getUnderwritingDetails (appId, uwDetailsModel, underwriter.getBusinessName());
        UnderwritingDetails savedUw = repository.save(uwDetails);
        documentService.saveAll(uwDetailsModel.getDocuments(), ssn, appId, savedUw.getId());
    }

    private UnderwritingDetails getUnderwritingDetails(Long appId, UnderwritingDetailsModel uwDetailsModel, String underwriterName) {
        UnderwritingDetails uwDetails = new UnderwritingDetails ();
        BeanUtils.copyProperties (uwDetailsModel,uwDetails);
        uwDetails.setAppId(appId);
        uwDetails.setStatus(uwDetailsModel.getStatus());
        uwDetails.setUnderwriterName (underwriterName);
        if(uwDetailsModel.getFailedRules() != null && !uwDetailsModel.getFailedRules().isEmpty()){
            uwDetails.setFailedRulesIds(uwDetailsModel.getFailedRules().toString().replace("[","").replace("]",""));
        }
        return uwDetails;
    }

    private void validarteRuleset(UnderwritingDetailsModel uwDetailsModel) {
        int rulesetVersion = uwDetailsModel.getRulesetVersion ();
        List<RuleModel> ruleSet = ruleService.getRules(rulesetVersion);
        if(ruleSet == null || ruleSet.isEmpty()){
            throw new IllegalArgumentException ("Given ruleset version "+rulesetVersion +" not found.");
        }
    }

}
