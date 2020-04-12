package com.uw.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
@ApiModel("Customer details.")
public class CustomerModel implements Serializable {
    @ApiModelProperty(notes="Customer identifier SSN.")
    @NotNull
    private String ssn;
    @ApiModelProperty(notes="Customer name.")
    @NotNull
    private String name;
    @ApiModelProperty(notes="Customer gender.")
    @NotNull
    private String gender;
    @ApiModelProperty(notes="Customer address.")
    @NotNull
    private String address;
    @ApiModelProperty(notes="Customer date of birth.")
    @NotNull
    @Past
    private Date dob;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
