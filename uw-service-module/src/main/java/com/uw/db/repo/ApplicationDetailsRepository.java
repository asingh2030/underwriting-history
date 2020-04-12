package com.uw.db.repo;

import com.uw.db.entities.ApplicationDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ApplicationDetailsRepository extends CrudRepository<ApplicationDetails, Long> {
    List<ApplicationDetails> findAllByCustomerId(String customerId);
    List<ApplicationDetails> findAllByCustomerIdAndCreatedDateBetween(String customerId, Date createdDateStart, Date createdDateEnd);
    @Query(value = "SELECT function('year', ad.createdDate) as year FROM ApplicationDetails ad " +
            "WHERE ad.customerId = :customerId GROUP BY function('year', ad.createdDate)")
    List<Integer> findAllByCustomerIdAndGroupByCreatedYear(String customerId);
}
