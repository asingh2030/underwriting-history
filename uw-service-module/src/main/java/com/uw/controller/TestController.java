package com.uw.controller;

import com.uw.db.entities.*;
import com.uw.db.repo.*;
import com.uw.model.ApplicationDetailsModel;
import com.uw.model.CustomerModel;
import com.uw.model.RuleModel;
import com.uw.model.UnderwriterModel;
import com.uw.service.ApplicationDetailsService;
import com.uw.service.RuleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController("/test")
@Transactional
public class TestController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UnderwriterRepository underwriterRepository;
    @Autowired
    ApplicationDetailsService detailsService;
    @Autowired
    RuleService ruleService;

    @ApiOperation(value = "Save customer basic details and return saved customer count.", response = Integer.class, tags = "saveCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PostMapping("/customers")
    public ResponseEntity<?> saveCustomer(@RequestBody List<CustomerModel> list){
        List<Customer> customers = list.stream ().map (this::mapToCustomer).collect (Collectors.toList ());
        Iterable<Customer> savedCustomers = customerRepository.saveAll (customers);
        return ResponseEntity.ok(savedCustomers.spliterator ().getExactSizeIfKnown ());
    }

    @ApiOperation(value = "Save Underwriter information.", response = Integer.class, tags = "saveUnderwriter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PostMapping("/underwriters")
    public ResponseEntity<?> saveUnderwriter(@RequestBody List<UnderwriterModel> list){
        List<Underwriter> underwriters = list.stream ().map (this::mapToUnderwriter).collect (Collectors.toList ());
        Iterable<Underwriter> savedDocs = underwriterRepository.saveAll (underwriters);
        return ResponseEntity.ok(savedDocs.spliterator ().getExactSizeIfKnown ());
    }

    @ApiOperation(value = "Create Ruleset associated with rules and returns ruleset version", response = String.class, tags = "saveRules")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PostMapping("/rules-set/{version}")
    public ResponseEntity<String> saveRules(@PathVariable int version, @RequestBody List<RuleModel> rules){
        if(rules == null || rules.isEmpty ()){
            throw new IllegalArgumentException("Given list must not be empty.");
        }
        ruleService.save(version, rules);
        return ResponseEntity.ok("OK");
    }

    @ApiOperation(value = "Save underwriting application detailed information.", response = Integer.class, tags = "saveApplicaiton")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!")})
    @PostMapping("/applications")
    public ResponseEntity<?> save(@RequestBody List<ApplicationDetailsModel> list){
        if(list == null || list.isEmpty ()){
            throw new IllegalArgumentException("Given list must not be empty.");
        }
        List<ApplicationDetails> applicationDetailsList = detailsService.saveApplications(list);
        return ResponseEntity.ok (applicationDetailsList.size());
    }

    private Customer mapToCustomer(CustomerModel model) {
        Customer entity = new Customer ();
        BeanUtils.copyProperties (model, entity);
        return entity;
    }

    private Underwriter mapToUnderwriter(UnderwriterModel underwriterModel) {
        Underwriter underwriter = new Underwriter ();
        BeanUtils.copyProperties (underwriterModel, underwriter);
        return underwriter;
    }

}
