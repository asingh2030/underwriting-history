package com.uw.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Map;

@ApiModel("Underwriting rule related details.")
public class RuleDetailsModel implements Serializable {
    @ApiModelProperty(value = "Name of rule.")
    private String name;
    @ApiModelProperty(value = "Rule description.")
    private String ruleDesc;
    @ApiModelProperty(value = "Configurable points its not added while failed.")
    private int points;
    @ApiModelProperty(value = "Execution status.")
    private String status;

    public RuleDetailsModel(String name, String ruleDesc, int points, String status) {
        this.name = name;
        this.ruleDesc = ruleDesc;
        this.points = points;
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
