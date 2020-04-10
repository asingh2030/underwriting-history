package com.uw.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "RULES")
@IdClass (RuleId.class)
public class Rule implements Serializable {
    @Id
    private String name;

    @JoinColumn(name = "version")
    @Id
    private int rulesetVersion;

    private String ruleDesc;
    private String parameters;
    public Rule(){}
    public Rule(int rulesetVersion, String name){
        this.name=name;
        this.rulesetVersion = rulesetVersion;
    }

    public int getRulesetVersion() {
        return rulesetVersion;
    }

    public void setRulesetVersion(int rulesetVersion) {
        this.rulesetVersion = rulesetVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return getRulesetVersion () == rule.getRulesetVersion () &&
                getName().equals(rule.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRulesetVersion (), getName());
    }
}
