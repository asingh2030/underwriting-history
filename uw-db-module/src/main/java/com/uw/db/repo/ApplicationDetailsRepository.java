package com.uw.db.repo;

import com.uw.db.model.ApplicationDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface ApplicationDetailsRepository extends CrudRepository<ApplicationDetails, Long> {
    List<ApplicationDetails> findAllByCustomerId(String customerId);
    List<ApplicationDetails> findAllByCustomerIdAndCreatedDateBetween(String customerId, Date createdDateStart, Date createdDateEnd);
}
