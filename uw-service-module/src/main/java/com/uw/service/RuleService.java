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
        List<Rule> rules = ruleRepository.findAllByRulesetVersion(rulesetVersion);
        if (rules != null && !rules.isEmpty()) {
            return rules.stream().map(this::mapToRuleModel).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }



    public List<RuleModel> getRules(String failedRulesIds, List<RuleModel> ruleModels) {
        List<RuleModel> rules = new ArrayList<>();
        if (failedRulesIds != null && !failedRulesIds.isEmpty()) {
            List<String> ruleNames = Arrays.asList(failedRulesIds.split(","));
            if (ruleNames != null && ruleNames.size() > 0) {
                List<RuleModel> ruleList = ruleModels.stream()
                        .filter(model ->
                                ruleNames.stream().anyMatch(name -> name.equals(model.getName()))
                        ).collect(Collectors.toList());
            }
            rules.addAll(rules);
        }
        return rules;
    }

    public void save(int version, List<RuleModel> rules) {
        RuleSet ruleSet = new RuleSet ();
        ruleSet.setVersion(version);
        RuleSet savedRuleset = ruleSetRepository.save(ruleSet);
        List<Rule> rulesList = rules.parallelStream ().map ((ruleModel) -> mapToRule(ruleModel, savedRuleset.getVersion())).collect (Collectors.toList ());
        Iterable<Rule> s = ruleRepository.saveAll(rulesList);
    }

    private Rule mapToRule(RuleModel ruleModel, int version) {
        Rule rule = new Rule ();
        rule.setRulesetVersion(version);
        rule.setName(ruleModel.getName());
        rule.setRuleDesc(ruleModel.getRuleDesc());
        rule.setParameters(ruleModel.getParameters().toString());
        return rule;
    }
    private RuleModel mapToRuleModel(Rule rule) {
        RuleModel ruleModel = new RuleModel();
        BeanUtils.copyProperties(rule, ruleModel);
        return ruleModel;
    }
}
