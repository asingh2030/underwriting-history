package com.uw.service;

import com.uw.db.entities.Customer;
import com.uw.db.repo.CustomerRepository;
import com.uw.exception.ResourceNotFoundException;
import com.uw.model.CustomerModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService{
    @Autowired
    private CustomerRepository repository;

    public CustomerModel getCustomerDetails(String ssn) {
        Assert.notNull(ssn,"Customer SSN must not be null.");
        Customer customer = repository.findBySsn(ssn);
        if(customer == null){
            throw new ResourceNotFoundException("Customer not found with given SSN - "+ssn);
        }
       return mapToModel(customer);
    }

    public List<CustomerModel> getAllCustomer() {
        Iterable<Customer> listIt = repository.findAll();
        List<Customer> list = StreamSupport.stream(listIt.spliterator(), false).collect(Collectors.toList());
        return getModels(list);
    }

    private List<CustomerModel> getModels(List<Customer> docList) {
        if (docList != null) {
            return docList.stream().map(CustomerService::mapToModel).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static CustomerModel mapToModel(Customer customer) {
        CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(customer, model);
        return model;
    }
}
