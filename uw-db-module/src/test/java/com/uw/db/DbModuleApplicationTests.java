package com.uw.db;

import com.uw.db.entities.*;
import com.uw.db.repo.*;
import com.uw.db.util.DocumentType;
import com.uw.db.util.UWStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DbModuleApplicationTests {
	@Autowired
	CustomerRepository custRepo;
	@Autowired
	DocumentRepository docRepo;
	@Autowired
	UnderwritingDetailsRepository uwRepository;
	@Autowired
	UnderwriterRepository underwriterRepository;
	@Autowired
	RuleSetRepository ruleSetRepository;
	@Autowired
	ApplicationDetailsRepository applicationDetailsRepository;


	@Test
	void testUnderwriter(){
		Underwriter alianz = underwriterRepository.findOneByBusinessName("Alianz");
		Assertions.assertEquals(alianz.getBusinessName(), "Alianz");
	}
	@Test
	void contextLoads() {
	}
	@Test
	public void saveDocTest() {
		try{
			Customer cust = getCustomer(1);
			custRepo.save (cust);
			List<Document> docs = getDocuments (cust, null, null);
			Iterable<Document> savedList = docRepo.saveAll (docs);
			Assertions.assertEquals (3,savedList.spliterator ().estimateSize ());
		}catch(Exception e){
			Assertions.fail(e.getMessage());
		}
	}
	@Test
	public void saveCustomerTest() {
		try{
			Customer cus = getCustomer (1);
			Customer savedCus = custRepo.save(cus);
			Assertions.assertNotNull(savedCus,"Saved customer cann't be null.");

			Customer bySsn = custRepo.findBySsn(cus.getSsn ());
			Assertions.assertEquals(cus, bySsn);

		}catch (Exception e){
			Assertions.fail(e.getMessage());
		}

	}
	@Test
	public void saveAllTest() {
		try {
			Customer cus = getCustomer (1);
			List<Document> docList = getDocuments (cus, null, null);
			custRepo.save (cus);
			List<Rule> ruleList = getRuleList (1);
			RuleSet ruleset = getRuleset (1, ruleList);
			ruleSetRepository.save (ruleset);
			Underwriter underwriter1 = getUnderwritier (1);
			underwriterRepository.save (underwriter1);
			ApplicationDetails app1 = getAppDetails (cus.getSsn (),1);
			ApplicationDetails saveedAppDetails = applicationDetailsRepository.save (app1);
			UnderwritingDetails uwDetails = getUwDetails (saveedAppDetails.getId (), "failed because of health issue", docList, ruleset.getVersion (), 6, UWStatus.FAILED, underwriter1);
			uwRepository.save (uwDetails);
		}catch (Exception e){
			Assertions.fail (e.getMessage ());
		}


	}

	private ApplicationDetails getAppDetails(String ssn, int i) {
		ApplicationDetails details = new ApplicationDetails(ssn, "AppCreator"+1);
		details.setCustomerAddress("Address of customer");
		return details;
	}

	private UnderwritingDetails getUwDetails(Long appId, String desc, List<Document> docList, int rulesetVersion, int score, UWStatus status, Underwriter underwriter) {
		UnderwritingDetails details = new UnderwritingDetails ();
		details.setAppId (appId);
		details.setDescription (desc);
		details.setDocumentsList (docList);
		details.setRulesetVersion (rulesetVersion);
		details.setScore (score);
		details.setStatus (status);
		details.setUnderwriter (underwriter);
		return details;
	}

	private RuleSet getRuleset(int i, List<Rule> list) {
		RuleSet ruleset = new RuleSet (i, list);
		return ruleset;
	}

	private List<Rule> getRuleList(int i) {
		List<Rule> list = new ArrayList<> ();
		Rule rule1 = new Rule (i,"Rule1");
		Rule rule2 = new Rule (i,"Rule1");
		Rule rule3 = new Rule (i,"Rule1");
		list.add (rule1);
		list.add (rule2);
		list.add (rule3);
		return list;
	}

	private Underwriter getUnderwritier(int i) {
		Underwriter underwriter = new Underwriter ();
		underwriter.setBusinessAcc ("123456789"+i);
		underwriter.setBusinessAddress ("Address"+i+", India");
		underwriter.setBusinessCategory ("insurance");
		underwriter.setBusinessDetails ("Insurance company");
		underwriter.setTaxId ("1234"+i);
		underwriter.setUnderwriterChannel ("BankChannel");
		underwriter.setBusinessName ("Bajaj Alliance");
		return underwriter;
	}



	private List<Document> getDocuments(Customer cust, Long uwId, Long appId) {
		Document doc1 = new Document("salary slip", DocumentType.PDF, cust.getSsn());
		Document doc2 = new Document("Account statement", DocumentType.PDF, cust.getSsn());
		Document doc3 = new Document("salary slip", DocumentType.PDF, cust.getSsn());
		doc1.setAppId (appId);
		doc2.setAppId (appId);
		doc3.setAppId (appId);
		doc1.setUwId (uwId);
		doc2.setUwId (uwId);
		doc3.setUwId (uwId);

		List<Document> docs = new ArrayList<> ();
		docs.add (doc1);
		docs.add (doc2);
		docs.add (doc3);
		return docs;
	}

	private Customer getCustomer(int i) throws ParseException {
		String dobString = "31/12/198"+i;
		Date dob = new SimpleDateFormat ("dd/MM/yyyy").parse(dobString);
		String ssn1 = "111111111"+i;
		Customer cus = new Customer (ssn1, "Customer"+1, "male", dob);
		return cus;
	}

}
