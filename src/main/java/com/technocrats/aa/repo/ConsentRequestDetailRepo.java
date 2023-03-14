package com.technocrats.aa.repo;

import com.technocrats.aa.model.ConsentRequestDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRequestDetailRepo extends MongoRepository<ConsentRequestDetail, String> {
}
