package com.technocrats.aa.repo;

import com.technocrats.aa.model.ConsentDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsentDetailRepo extends MongoRepository<ConsentDetail, String> {

    @Query("{$and: [{'consentId': {$eq: ?0}}, {'dataManager.name': {$eq: ?1}}, {'status': {$eq: ?2}}, {'errorInfo': {$exists: false}}]}")
    ConsentDetail findByConsentIdAndDataManagerNameAndStatus(String consentId, String dataManagerName, String status);

    @Query("{$and: [{'consentId': {$eq: ?0}}, {'errorInfo': {$exists: true}}, {'dataManager.name': {$eq: ?1}}]}")
    List<ConsentDetail> findAllFailedArtefactFetch(String consentId, String dataManagerName);

    @Query("{$and: [ {'errorInfo': {$exists: false}}, {'requestId': {$exists: false}} ]}")
    List<ConsentDetail> findAllArtefactsWithAbsentRequestId();

    @Query("{$and: [ {'errorInfo': {$exists: false}}, {'consentId': {$eq: ?0}}, {'dataManager.name': {$eq: ?1}} ]}")
    ConsentDetail findByConsentIdAndDataManagerName(String consentId, String dataManagerName);

//    @Query("{$and: [{'consentId': {$eq: ?0}}, {'errorInfo': {$exists: false}}]}, {$set: {'requestId': ?1}}")
//    ConsentArtefactDetail updateWithConsentHandleAndRequestId(String consentId, String requestId, String consentHandle);
}
