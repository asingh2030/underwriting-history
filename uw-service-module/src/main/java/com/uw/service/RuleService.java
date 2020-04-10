package com.uw.service;

import com.uw.db.entities.Rule;
import com.uw.db.entities.RuleSet;
import com.uw.db.repo.RuleRepository;
import com.uw.db.repo.RuleSetRepository;
import com.uw.model.RuleModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleService {
    @Autowired
    private RuleSetRepository ruleSetRepository;
    @Autowired
    private RuleRepository ruleRepository;

    public List<RuleModel> getRules(int rulesetVersion) {
        RuleSet ruleSet = ruleSetRepository.findByVersion(rulesetVersion);
        if(ruleSet != null){
            List<Rule> rules = ruleSet.getRules ();
            if(rules != null && !rules.isEmpty ()){
                return rules.stream ().map (this::mapToRule).collect (Collectors.toList ());
            }
        }
        return Collections.emptyList ();
    }

    private RuleModel mapToRule(Rule rule) {
        RuleModel ruleModel = new RuleModel ();
        BeanUtils.copyProperties (rule, ruleModel);
        return ruleModel;
    }

    public List<RuleModel> getRules(String failedRulesIds, List<RuleModel> ruleModels) {
        List<RuleModel> rules = new ArrayList<> ();
        if(failedRulesIds != null && !failedRulesIds.isEmpty ()){
            List<String> ids = Arrays.asList (failedRulesIds.split (","));
            if(ids != null && ids.size () > 0){
                List<RuleModel> ruleList = ruleModels.stream ()
                        .filter (model->
                            ids.stream ().anyMatch (id -> id.equals (model.getId ()))
                ).collect (Collectors.toList ());
            }
            rules.addAll (rules);
        }
        return rules;
    }
}
