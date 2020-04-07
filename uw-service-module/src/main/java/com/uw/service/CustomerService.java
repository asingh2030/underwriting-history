package com.uw.service;

import com.uw.db.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface CustomerService<T> {
    T getCustomerDetails(String ssn);
}
