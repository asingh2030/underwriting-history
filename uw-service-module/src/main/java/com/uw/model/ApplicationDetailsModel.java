package com.uw.model;

import com.uw.util.ApplicationStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
@ApiModel(description="All details about the customer underwriting details. ")
public class ApplicationDetailsModel implements Serializable {
    @ApiModelProperty(notes="Customer SSN.")
    @NotNull
    private String customerId;
    @ApiModelProperty(notes="Application created date.")
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdDate;
    @ApiModelProperty(notes="Application crearted by insurance manager name.")
    private String createdBy;
    @ApiModelProperty(notes="Application last modified date.")
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date modifiedDate;
    @ApiModelProperty(notes="Customer address when he's applied for underwriting.")
    private String customerAddress;
    @ApiModelProperty(notes="Application last modified by.")
    private String modifiedBy;
    @ApiModelProperty(notes="Application status.")
    @NotNull
    private ApplicationStatus status;
    private UnderwritingDetailsModel uwDetailsModel;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public ApplicationDetailsModel(){
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public UnderwritingDetailsModel getUwDetailsModel() {
        return uwDetailsModel;
    }

    public void setUwDetailsModel(UnderwritingDetailsModel uwDetailsModel) {
        this.uwDetailsModel = uwDetailsModel;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
