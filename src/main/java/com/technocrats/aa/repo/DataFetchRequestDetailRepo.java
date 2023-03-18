package com.technocrats.aa.repo;

import com.technocrats.aa.model.DataFetchRequestDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataFetchRequestDetailRepo extends MongoRepository<DataFetchRequestDetail, String> {

    @Query("{$and: [{'errorInfo': {$exists: false}}, {'sessionDetails.sessionId': {$eq: ?0}}]}")
    DataFetchRequestDetail findBySessionDetailsSessionId(String sessionId);

    @Query("{$and: [ {'errorInfo': {$exists: false}}, { $or: [{'consentId': {$exists: false}}, {'requestId': {$exists: false}}] } ]}")
    List<DataFetchRequestDetail> findByConsentIdNullOrRequestIdNullOrBoth();
}
