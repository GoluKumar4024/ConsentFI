package com.technocrats.aa.repo;

import com.technocrats.aa.model.FIFetchDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FIFetchDetailRepo extends MongoRepository<FIFetchDetail, String> {

    FIFetchDetail findBySessionIdAndAccAggName(String sessionId, String dataManagerName);

    @Query("{$and: [{'consentId': {$exists: false}},{'requestId': {$exists: false}}, {'errorInfo': {$exists: false}}]}")
    List<FIFetchDetail> findByConsentIdOrRequestIdOrBothNull();

}
