package com.uw.service;

import com.uw.db.entities.Customer;
import com.uw.db.repo.CustomerRepository;
import com.uw.model.ApplicationDetailsModel;
import com.uw.model.DocumentModel;
import com.uw.model.UnderwritingDetailsModel;
import com.uw.util.ApplicationStatus;
import com.uw.util.DocumentType;
import com.uw.util.UWStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class UwServiceModuleApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ApplicationDetailsService detailsService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSaveUwApplication(){
		try{
			Customer customer = getCustomer();
			ApplicationDetailsModel applicationDetailsModel = getApplication("11111","21/01/2018");
		}catch(Exception e){
			Assertions.fail("FAILED : "+e.getMessage());
		}
	}


	private ApplicationDetailsModel getApplication(String ssn, String dateStr) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
		ApplicationDetailsModel appModel = new ApplicationDetailsModel();
		appModel.setCreatedDate(date);
		appModel.setCreatedBy("App1");
		appModel.setCustomerAddress("Pune, India");
		appModel.setCustomerId(ssn);
		appModel.setModifiedBy("App1");
		appModel.setStatus(ApplicationStatus.CANCELED);
		appModel.setModifiedDate(date);
		appModel.setUwDetailsModel(getUwDetails(date, 1, "Alianze",ssn));
		return appModel;
	}

	private UnderwritingDetailsModel getUwDetails(Date date, int ruleVersion, String underwriterName, String ssn) {
		UnderwritingDetailsModel model = new UnderwritingDetailsModel();
		model.setCreatedDate(date);
		model.setDescription("Cunstomer not required document in given timeline.");
		model.setModifiedDate(date);
		model.setRulesetVersion(ruleVersion);
		model.setUnderwriterName(underwriterName);
		model.setScore(0);
		model.setStatus(UWStatus.CANCELED);
		model.setDocuments(getDocList());
		model.setRulesetVersion(1);
		return model;
	}

	private List<DocumentModel> getDocList() {
		DocumentModel doc1 = new DocumentModel();
		doc1.setDocumentName("CustomerHealthDoc");
		doc1.setDocumentType(DocumentType.PDF);
		DocumentModel doc2 = new DocumentModel();
		doc2.setDocumentName("CustomerFinancialDoc");
		doc2.setDocumentType(DocumentType.PDF);
		List<DocumentModel> ls = new ArrayList<>();
		ls.add(doc1);
		ls.add(doc2);
		return ls;
	}

	private Customer getCustomer() throws ParseException {
		String dobString="31/12/1984";
		Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(dobString);
		String ssn1 = "1111111111";
		Customer cus = new Customer(ssn1, "Customer1", "male", dob);
		return cus;
	}


}
