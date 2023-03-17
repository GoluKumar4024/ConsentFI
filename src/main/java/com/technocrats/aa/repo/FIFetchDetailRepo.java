package com.technocrats.aa.repo;

import com.technocrats.aa.model.FIFetchDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FIFetchDetailRepo extends MongoRepository<FIFetchDetail, String> {

    FIFetchDetail findBySessionIdAndAccAggName(String sessionId, String dataManagerName);

}
