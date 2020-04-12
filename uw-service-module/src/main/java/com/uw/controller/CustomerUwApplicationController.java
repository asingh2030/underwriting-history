package com.uw.controller;

import com.uw.db.entities.Customer;
import com.uw.model.*;
import com.uw.service.ApplicationDetailsService;
import com.uw.service.CustomerService;
import com.uw.service.UnderwritingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "CustomerUwApplicationController", description = "REST APIs related to Customer underwriting application!!!!")
@RestController
@RequestMapping("/customers")
public class CustomerUwApplicationController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ApplicationDetailsService appService;
    @Autowired
    private UnderwritingService uwService;

    @ApiOperation(value = "Get list of years that customer was applied for underwriting application.", response = Iterable.class, tags = "getAppYears")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/search")
    public List<Integer> getAppYears(@RequestParam(value = "ssn", required = true) String ssn){
        return appService.getAllYearsBySsn(ssn);
    }

    @ApiOperation(value = "Get underwriting applications of customer of given year.", response = Iterable.class, tags = "getAppDetailsPerYear")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/{ssn}/app-details")
    public ResponseEntity<List> getAppDetailsPerYear(@PathVariable("ssn") String ssn, @RequestParam(value = "year",required = true) int year){
        List<AppDetails> appList = appService.getAppDetailsBySsnAndYear(ssn, year);
        return new ResponseEntity<>(appList, HttpStatus.OK);
    }
    @ApiOperation(value = "Get underwriting application details by given application id.", response = UwDetails.class, tags = "getAppDetails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/{ssn}/app-details/{appId}")
    public ResponseEntity<UwDetails> getAppDetails(@PathVariable("ssn") String ssn, @PathVariable(value = "appId",required = true) Long appId){
        UwDetails model = uwService.getUwDetailsByAppId(appId);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all customers.", response = Customer.class, tags = "findAllCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/all")
    public List<CustomerModel> findAllCustomer(){
        return service.getAllCustomer();
    }

    @ApiOperation(value = "Get customer basic information of given customer identifier.", response = Iterable.class, tags = "findCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/{ssn}")
    public HttpEntity<CustomerModel> findCustomer(@PathVariable String ssn){
        CustomerModel customer = service.getCustomerDetails(ssn);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all customers and its applications history.", response = Customer.class, tags = "findAllHistory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/app-details/all")
    public List<UnderwritingHistoryDetailsModel> findAll(){
        List<CustomerModel> allCustomer = service.getAllCustomer();
        List<UnderwritingHistoryDetailsModel> historyList = allCustomer.stream().map(customer -> {
            List<ApplicationDetailsModel> allApp = appService.getAllAppBySsn(customer.getSsn());
            UnderwritingHistoryDetailsModel model = new UnderwritingHistoryDetailsModel(customer, allApp);
            return model;
        }).collect(Collectors.toList());
        return historyList;
    }
}

