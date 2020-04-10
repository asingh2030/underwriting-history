package com.uw.controller;

import com.uw.db.entities.Customer;
import com.uw.exception.ResourceNotFoundException;
import com.uw.model.ApplicationDetailsModel;
import com.uw.model.CustomerModel;
import com.uw.model.UnderwritingDetailsModel;
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
        List<ApplicationDetailsModel> appList = appService.getApplicationDetailsBySsnAndYear(ssn, year);
//        CollectionModel<ApplicationDetailsModel> resource = new CollectionModel<>(appList);
        //TODO: Link to getAppDetails
//        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(CustomerUwApplicationController.class).getAppDetails());
//        resource.add(webMvcLinkBuilder.withSelfRel());
        return new ResponseEntity<>(appList, HttpStatus.OK);
    }

    /*@GetMapping("/{ssn}/uw-details/{uwId}")
    public ResponseEntity<UnderwritingDetailsModel> getUwDetails(@PathVariable("ssn") String ssn, @PathVariable("uwId") Long uwId){
        UnderwritingDetailsModel model = uwService.getUwDetailsById(uwId);
        if(model == null){
            throw new ResourceNotFoundException(String.format("Given underwriting id not found - {%s}",uwId.toString()));
        }
//        EntityModel<UnderwritingDetailsModel> resource = new EntityModel<>(model);
        //TODO: add hateoas link
        return new ResponseEntity(model, HttpStatus.OK);
    }*/

    @ApiOperation(value = "Get underwriting details of given application and customer.", response = Iterable.class, tags = "getUwDetails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/{ssn}/uw-details/{appId}")
    public ResponseEntity<List> getUwDetails(@PathVariable("ssn") String ssn, @PathVariable("appId") Long appId){
        List<UnderwritingDetailsModel> model = uwService.getAllUwDetailsByAppId (appId);
        if(model == null){
            throw new ResourceNotFoundException(String.format("Given application id not found - {%s}",appId.toString()));
        }
//        EntityModel<UnderwritingDetailsModel> resource = new EntityModel<>(model);
        //TODO: add hateoas link
        return new ResponseEntity(model, HttpStatus.OK);
    }


    @ApiOperation(value = "Get Application details of given application id and customer.", response = ApplicationDetailsModel.class, tags = "getAppDetails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/{ssn}/app-details/{appId}")
    public ResponseEntity<ApplicationDetailsModel> getAppDetails(@PathVariable("ssn") String ssn, @PathVariable("appId") Long appId){
        ApplicationDetailsModel model = appService.getAppDetails(appId);
//        EntityModel<ApplicationDetailsModel> resource = new EntityModel<>(appList);
        /*WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(CustomerUwApplicationController.class).findAppDetails());
        resource.add(webMvcLinkBuilder.withSelfRel());*/
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
//        EntityModel<CustomerModel> resource = new EntityModel<CustomerModel>(customer);

//        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(CustomerUwApplicationController.class).findAllCustomer());

//        resource.add(webMvcLinkBuilder.withSelfRel());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
