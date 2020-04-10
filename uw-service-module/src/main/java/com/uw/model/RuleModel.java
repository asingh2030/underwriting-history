package com.uw.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Map;

@ApiModel("Underwriting rule related details.")
public class RuleModel implements Serializable {
    @ApiModelProperty(value = "Unique id of rule.")
    private Long id;
    @ApiModelProperty(value = "version of rule.")
    private int rulesetVersion;
    @ApiModelProperty(value = "Name of rule.")
    private String name;
    @ApiModelProperty(value = "Rule description.")
    private String ruleDesc;
    //TODO: store all parameters map into string - write conversion
    @ApiModelProperty(value = "Configurable parameters.")
    private Map<String, String> parameters;

    public RuleModel(){
    }
    public RuleModel(int version, String name){
        this.name=name;
        this.rulesetVersion =version;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
