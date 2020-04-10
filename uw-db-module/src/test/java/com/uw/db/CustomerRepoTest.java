package com.uw.db;

import com.uw.db.entities.Customer;
import com.uw.db.repo.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerRepoTest extends DbModuleApplicationTests{
    @Autowired
    CustomerRepository custRepo;

    @Test
    public void saveCustomerTest() {
        try{
            String dobString="31/12/1984";
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(dobString);
            String ssn1 = "1111111111";
            Customer cus = new Customer(ssn1, "Customer1", "male", dob);
            Customer savedCus = custRepo.save(cus);
            Assertions.assertNotNull(savedCus,"Saved customer cann't be null.");

            Customer bySsn = custRepo.findBySsn(ssn1);
            Assertions.assertEquals(cus, bySsn);

        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }

    }
}
