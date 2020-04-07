package com.uw.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class RuleSetModel implements Serializable {
    private Long id;
    private int version;
    private Set<RuleModel> rules;
    private LocalDate createdDate;

    public RuleSetModel(int version, List<RuleModel> rules, LocalDate createdDate) {
        this.version = version;
        this.rules = new HashSet<>();
        this.rules.addAll(rules);
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    protected void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
