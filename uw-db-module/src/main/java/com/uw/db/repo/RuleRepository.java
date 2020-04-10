package com.uw.db.repo;

import com.uw.db.entities.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends CrudRepository<Rule, Long> {
    List<Rule> findAllByRulesetVersion(int version);
}
