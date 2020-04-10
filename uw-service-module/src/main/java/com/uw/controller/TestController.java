package com.uw.controller;

import com.uw.db.entities.*;
import com.uw.db.repo.*;
import com.uw.exception.ResourceNotFoundException;
import com.uw.model.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("/test")
@Transactional
public class TestController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ApplicationDetailsRepository applicationDetailsRepository;
    @Autowired
    UnderwriterRepository underwriterRepository;
    @Autowired
    UnderwritingDetailsRepository underwritingDetailsRepository;
    @Autowired
    RuleSetRepository ruleSetRepository;
    @Autowired
    private DocumentRepository documentRepo;

    @ApiOperation(value = "Save customer basic details and return saved customer count.", response = Integer.class, tags = "saveCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PostMapping("/customers")
    public ResponseEntity<?> saveCustomer(@RequestBody List<CustomerModel> list){
        List<Customer> customers = list.stream ().map (this::mapToCustomer).collect (Collectors.toList ());
        Iterable<Customer> savedCustomers = customerRepository.saveAll (customers);
        return ResponseEntity.ok(savedCustomers.spliterator ().getExactSizeIfKnown ());
    }

    private Customer mapToCustomer(CustomerModel model) {
        Customer entity = new Customer ();
        BeanUtils.copyProperties (model, entity);
        return entity;
    }
    @ApiOperation(value = "Save documents belonging to application or customer.", response = Integer.class, tags = "saveDocuments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PostMapping("/documents")
    public ResponseEntity<?> saveDocuments(@RequestBody List<DocumentModel> list){
        List<Document> docs = list.stream ().map (this::mapToDocument).collect (Collectors.toList ());
        Iterable<Document> savedDocs = documentRepo.saveAll (docs);
        return ResponseEntity.ok(savedDocs.spliterator ().getExactSizeIfKnown ());
    }

    private Document mapToDocument(DocumentModel documentModel) {
        Document doc = new Document ();
        BeanUtils.copyProperties (documentModel,doc);
        doc.setDocumentType(documentModel.getDocumentType());
        return doc;
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

    private Underwriter mapToUnderwriter(UnderwriterModel underwriterModel) {
        Underwriter underwriter = new Underwriter ();
        BeanUtils.copyProperties (underwriterModel, underwriter);
        return underwriter;
    }

    @ApiOperation(value = "Create Ruleset associated with rules and returns ruleset version", response = Integer.class, tags = "saveRules")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PostMapping("/rules-set/{version}")
    public ResponseEntity<?> saveRules(@PathVariable int version, @RequestBody List<RuleModel> rules){
        if(rules == null || rules.isEmpty ()){
            throw new IllegalArgumentException("Given list must not be empty.");
        }
        List<Rule> rulesList = rules.parallelStream ().map ((ruleModel) -> mapToRule(ruleModel, version)).collect (Collectors.toList ());
        RuleSet ruleSet = new RuleSet (version,rulesList);
        ruleSetRepository.save(ruleSet);
        return ResponseEntity.ok (ruleSet.getVersion ());
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
        List<ApplicationDetails> applicationDetailsList = list.parallelStream ().map (this::mapToAppDetails).collect (Collectors.toList ());
        Iterable<ApplicationDetails> savedList = applicationDetailsRepository.saveAll (applicationDetailsList);
        updateAppId(savedList);
        return ResponseEntity.ok (savedList.spliterator ().getExactSizeIfKnown ());
    }

    private void updateAppId(Iterable<ApplicationDetails> savedList) {
        savedList.spliterator().forEachRemaining(details->{
            Long appId = details.getId();
            UnderwritingDetails uw = details.getUwList().stream().findAny().get();
            uw.setAppId(appId);
            underwritingDetailsRepository.save(uw);
            List<Document> documentList = details.getDocumentList();
            documentList.stream().forEach(doc->{
                doc.setAppId(appId);
                doc.setUwId(uw.getId());
                documentRepo.save(doc);
            });
        });
    }

    private ApplicationDetails mapToAppDetails(ApplicationDetailsModel appModel) {
        String ssn = appModel.getCustomerId ();
        Customer customer = getCustomer (ssn);

        UnderwritingDetailsModel uwDetailsModel = appModel.getUwDetailsModel ();
        Underwriter underwriter = getUnderwriter (uwDetailsModel);
        List<Document> docs = getDocuments (uwDetailsModel);

        validarteRuleset (uwDetailsModel);
        UnderwritingDetails uwDetails = getUnderwritingDetails (uwDetailsModel, underwriter, docs);
        List<UnderwritingDetails> uwList = new ArrayList<> ();
        uwList.add(uwDetails);
        ApplicationDetails app = new ApplicationDetails ();
        BeanUtils.copyProperties (appModel,app);
        app.setStatus(appModel.getStatus());
        app.setUwList (uwList);
        app.setDocumentList (docs);
        return app;
    }

    private UnderwritingDetails getUnderwritingDetails(UnderwritingDetailsModel uwDetailsModel, Underwriter underwriter, List<Document> docs) {
        UnderwritingDetails uwDetails = new UnderwritingDetails ();
        BeanUtils.copyProperties (uwDetailsModel,uwDetails);
        uwDetails.setStatus(uwDetailsModel.getStatus());
        uwDetails.setUnderwriter (underwriter);
        uwDetails.setDocumentsList (docs);
        if(uwDetailsModel.getFailedRules() != null && !uwDetailsModel.getFailedRules().isEmpty()){
            uwDetails.setFailedRulesIds(uwDetailsModel.getFailedRules().toString().replace("[","").replace("]",""));
        }
        return uwDetails;
    }

    private void validarteRuleset(UnderwritingDetailsModel uwDetailsModel) {
        int rulesetVersion = uwDetailsModel.getRulesetVersion ();
        RuleSet ruleSet = ruleSetRepository.findByVersion (rulesetVersion);
        if(ruleSet == null){
            throw new IllegalArgumentException ("Given ruleset version "+rulesetVersion +" not found.");
        }
    }

    private Underwriter getUnderwriter(UnderwritingDetailsModel uwDetailsModel) {
        Underwriter underwriter = underwriterRepository.findOneByBusinessName (uwDetailsModel.getUnderwriterName ());
        if(underwriter == null){
//            underwriterRepository.save (mapToUnderwriter (uwDetailsModel.getUnderwriter ()));
            throw new ResourceNotFoundException (uwDetailsModel.getUnderwriterName () + " named underwriter not found.");
        }
        return underwriter;
    }

    private List<Document> getDocuments(UnderwritingDetailsModel uwDetailsModel) {
        List<DocumentModel> documentModels = uwDetailsModel.getDocuments ();
        return documentModels.stream ().map (this::mapToDocument).collect (Collectors.toList ());
    }

    private Customer getCustomer(String ssn) {
        Customer customer = customerRepository.findBySsn (ssn);
        if(customer == null){
            throw new ResourceNotFoundException ("Given customer "+ssn+" is not exist.");
        }
        return customer;
    }

    private Rule mapToRule(RuleModel ruleModel, int version) {
        Rule rule = new Rule (version,ruleModel.getName ());
        rule.setParameters(ruleModel.getParameters().toString());
        rule.setRuleDesc(ruleModel.getRuleDesc());
        return rule;
    }
}
