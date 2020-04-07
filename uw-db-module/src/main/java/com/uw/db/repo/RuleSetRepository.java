package com.uw.db.repo;

import com.uw.db.model.RuleSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleSetRepository extends CrudRepository<RuleSet, Long> {
    RuleSet findByVersion(int version);
}
