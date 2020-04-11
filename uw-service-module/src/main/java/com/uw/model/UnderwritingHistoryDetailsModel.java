package com.uw.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UnderwritingHistoryDetailsModel implements Serializable {
    private CustomerModel customer;
    private List<ApplicationDetailsModel> appHistoryList;
    private LocalDateTime requestTime;

    public UnderwritingHistoryDetailsModel(CustomerModel customer, List<ApplicationDetailsModel> appHistoryList){
        this.customer = customer;
        this.appHistoryList = new ArrayList<>(appHistoryList);
        this.requestTime = LocalDateTime.now();
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public List<ApplicationDetailsModel> getAppHistoryList() {
        return appHistoryList;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
