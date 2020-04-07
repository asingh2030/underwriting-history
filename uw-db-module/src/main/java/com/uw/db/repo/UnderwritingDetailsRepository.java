package com.uw.db.repo;

import com.uw.db.model.UnderwritingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UnderwritingDetailsRepository extends CrudRepository<UnderwritingDetails, Long> {
    List<UnderwritingDetails> findByAppId(Long appId);
}
