package com.uw.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "RULE")
public class Rule implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Version
    private int version;
    private String name;
    private String ruleDesc;
    //TODO: store all parameters map into string - write conversion
    private String parameters;

    Rule(Long id, int version, String name){
        this.id=id;
        this.name=name;
        this.version=version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
        return getVersion() == rule.getVersion() &&
                getId().equals(rule.getId()) &&
                getName().equals(rule.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVersion(), getName());
    }
}
