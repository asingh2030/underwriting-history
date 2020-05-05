package com.uw.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel("Application rules details.")
public class AppRulesModel {
    @ApiModelProperty(value = "Customer identifier.")
    private String ssn;
    @ApiModelProperty(value = "Application indentifier.")
    private Long appId;
    @ApiModelProperty(value = "Ruleset version.")
    private int rulesetVersion;
    @ApiModelProperty(value = "List of rules used while process underwriting.")
    private List<RuleDetailsModel> rules;

    public AppRulesModel(String ssn, Long appId, int version, List<RuleDetailsModel> rules) {
        this.ssn = ssn;
        this.appId = appId;
        this.rules = rules;
        this.rulesetVersion = version;
    }

    public int getRulesetVersion() {
        return rulesetVersion;
    }

    public void setRulesetVersion(int rulesetVersion) {
        this.rulesetVersion = rulesetVersion;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public List<RuleDetailsModel> getRules() {
        return rules;
    }

    public void setRules(List<RuleDetailsModel> rules) {
        this.rules = rules;
    }
}
