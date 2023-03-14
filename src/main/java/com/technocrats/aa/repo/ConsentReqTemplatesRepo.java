package com.technocrats.aa.repo;

import com.technocrats.aa.model.ConsentReqTemplates;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentReqTemplatesRepo extends MongoRepository<ConsentReqTemplates, String> {

    ConsentReqTemplates findByPurposeCode(String purposeCode);
}
