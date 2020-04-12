package com.uw.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "RULESET")
public class RuleSet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private int version;
    @OneToMany(mappedBy = "rulesetVersion", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Rule> rules;
    private Date createdDate;
    public RuleSet(){}
    public RuleSet(int version, List<Rule> rules) {
        this.version = version;
        this.rules = new HashSet<>(rules);
        this.createdDate = new Date ();
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

    public List<Rule> getRules() {
        List<Rule> list = new ArrayList<>(rules);
        List<Rule> rules = Collections.unmodifiableList(list);
        return rules;
    }


    protected void setRules(List<Rule> rules) {
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
