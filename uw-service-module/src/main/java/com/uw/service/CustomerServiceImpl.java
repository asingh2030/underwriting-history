package com.uw.service;

import com.uw.db.model.Customer;
import com.uw.db.repo.CustomerRepository;
import com.uw.exception.UserNotFoundException;
import com.uw.model.CustomerModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CustomerServiceImpl implements CustomerService<CustomerModel>{
    @Autowired
    private CustomerRepository repository;

    @Override
    public CustomerModel getCustomerDetails(String ssn) {
        Assert.notNull(ssn,"Customer SSN must not be null.");
        Customer customer = repository.findBySsn(ssn);
        if(customer == null){
            throw new UserNotFoundException("Customer not found with given SSN - "+ssn);
        }
        CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(customer, model);
        return model;
    }
}
