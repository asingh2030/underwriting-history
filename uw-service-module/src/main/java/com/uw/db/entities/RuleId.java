package com.uw.db.entities;

import java.io.Serializable;
import java.util.Objects;

public class RuleId implements Serializable {
    private int rulesetVersion;
    private String name;

    RuleId(){
    }
    public RuleId(int rulesetVersion, String name){
        this.rulesetVersion = rulesetVersion;
        this.name = name;
    }

    public int getRulesetVersion() {
        return rulesetVersion;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleId ruleId = (RuleId) o;
        return rulesetVersion == ruleId.rulesetVersion &&
                Objects.equals(name, ruleId.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rulesetVersion, name);
    }
}
