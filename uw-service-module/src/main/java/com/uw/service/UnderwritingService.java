package com.uw.service;

import com.uw.db.entities.Document;
import com.uw.db.entities.Underwriter;
import com.uw.db.entities.UnderwritingDetails;
import com.uw.db.repo.UnderwritingDetailsRepository;
import com.uw.model.UnderwriterModel;
import com.uw.model.UnderwritingDetailsModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnderwritingService {
    @Autowired
    private UnderwritingDetailsRepository repository;
    @Autowired
    private RuleService ruleService;
    @Autowired
    private DocumentService documentService;

    public List<UnderwritingDetailsModel> getAllUwDetailsByAppId(Long appId){
        Assert.notNull(appId, "Application Id is must to fetch underwriting details.");
        List<UnderwritingDetails> list = repository.findAllByAppId(appId);
        return getUnderwritingDetailsModel(list);
    }

    public UnderwritingDetailsModel getUwDetailsById(Long uwId){
        Assert.notNull(uwId, "Given underwriting Id is must tnot null.");
        Optional<UnderwritingDetails> optional = repository.findById(uwId);
        if(optional.isPresent()){
            return mapToUwModel(optional.get());
        }
        return null;
    }

    public List<UnderwritingDetailsModel> getUnderwritingDetailsModel(List<UnderwritingDetails> list) {
        if (list != null) {
            return list.stream().map(this::mapToUwModel).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public UnderwritingDetailsModel mapToUwModel(UnderwritingDetails details) {
        UnderwritingDetailsModel uwModel = new UnderwritingDetailsModel ();
        BeanUtils.copyProperties (details, uwModel);

        List<Document> documentsList = details.getDocumentsList ();
        if(documentsList != null && !documentsList.isEmpty ()){
            uwModel.setDocuments (documentService.getDocumentModels (documentsList));
        }

        Underwriter underwriter = details.getUnderwriter ();
        UnderwriterModel underwriterModel = new UnderwriterModel ();
        BeanUtils.copyProperties (underwriter, underwriterModel);
        uwModel.setUnderwriter (underwriterModel);

        int rulesetVersion = details.getRulesetVersion ();
        uwModel.setRules (ruleService.getRules (rulesetVersion));
        String failedRulesIds = details.getFailedRulesIds ();
        if(failedRulesIds != null && !failedRulesIds.isEmpty ()){
            uwModel.setFailedRules (Arrays.asList (failedRulesIds.split (",")));
        }
        return uwModel;
    }

}
