package com.uw.db.repo;

import com.uw.db.entities.UnderwritingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnderwritingDetailsRepository extends CrudRepository<UnderwritingDetails, Long> {
    List<UnderwritingDetails> findAllByAppId(Long appId);
}
