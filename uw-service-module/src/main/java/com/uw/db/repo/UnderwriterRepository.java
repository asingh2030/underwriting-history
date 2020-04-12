package com.uw.db.repo;

import com.uw.db.entities.Underwriter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnderwriterRepository extends CrudRepository<Underwriter, Long> {
    Underwriter findOneByBusinessName(String name);
}
