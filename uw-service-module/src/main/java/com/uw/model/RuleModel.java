package com.uw.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Map;

@ApiModel("Underwriting rule related details.")
public class RuleModel implements Serializable {
    @ApiModelProperty(value = "Name of rule.")
    private String name;
    @ApiModelProperty(value = "Rule description.")
    private String ruleDesc;
    @ApiModelProperty(value = "Configurable parameters.")
    private Map<String, String> parameters;

    public RuleModel(){
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

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
