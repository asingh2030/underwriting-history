package com.uw.db.repo;

import com.uw.db.entities.RuleSet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleSetRepository extends CrudRepository<RuleSet, Long> {
    RuleSet findByVersion(int version);
    @Query("select MAX(rs.version) from RuleSet rs")
    int findMaxValueOfVersion();
}
