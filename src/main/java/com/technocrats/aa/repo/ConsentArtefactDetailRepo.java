package com.technocrats.aa.repo;

import com.technocrats.aa.model.ConsentArtefactDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsentArtefactDetailRepo extends MongoRepository<ConsentArtefactDetail, String> {

    @Query("{$and: [{'consentId': {$eq: ?0}}, {'errorInfo': {$exists: false}}]}")
    ConsentArtefactDetail findByConsentId(String consentId);

    @Query("{$and: [{'consentId': {$eq: ?0}}, {'errorInfo': {$exists: true}}]}")
    List<ConsentArtefactDetail> findAllFailedArtefactFetch(String consentId);

    @Query("{$and: [ {'errorInfo': {$exists: false}}, { $or: [{'consentHandleId': {$exists: false}}, {'requestId': {$exists: false}}] } ]}")
    List<ConsentArtefactDetail> findAllArtefactsWithAbsentRequestAndHandle();

//    @Query("{$and: [{'consentId': {$eq: ?0}}, {'errorInfo': {$exists: false}}]}, {$set: {'requestId': ?1}}")
//    ConsentArtefactDetail updateWithConsentHandleAndRequestId(String consentId, String requestId, String consentHandle);
}
