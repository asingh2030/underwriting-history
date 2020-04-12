package com.uw.db.repo;

import com.uw.db.entities.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, String> {

    List<Document> findByCustomerId(String customerId);
    List<Document> findByUwId(Long uwId);
}
