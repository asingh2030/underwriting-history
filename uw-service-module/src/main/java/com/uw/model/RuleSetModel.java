package com.uw.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.*;
@ApiModel("Underwriting ruleset details.")
public class RuleSetModel implements Serializable {
    @ApiModelProperty(notes="Ruleset unique version.")
    private int version;
    @ApiModelProperty(notes="Rules group in this ruleset.")
    private Set<RuleModel> rules;
    @ApiModelProperty(notes="Ruleset created date.")
    private Date createdDate;

    public RuleSetModel(int version, List<RuleModel> rules, Date createdDate) {
        this.version = version;
        this.rules = new HashSet<>();
        this.rules.addAll(rules);
        this.createdDate = createdDate;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    public List<RuleModel> getRules() {
        List<RuleModel> list = new ArrayList<>(rules);
        List<RuleModel> rules = Collections.unmodifiableList(list);
        return rules;
    }


    protected void setRules(List<RuleModel> rules) {
        this.rules = new HashSet<>();
        this.rules.addAll(rules);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    protected void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
