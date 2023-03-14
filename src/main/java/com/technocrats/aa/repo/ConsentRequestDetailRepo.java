package com.technocrats.aa.repo;

import com.technocrats.aa.model.ConsentRequestDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsentRequestDetailRepo extends MongoRepository<ConsentRequestDetail, String> {

    @Query(value = "{$and: [{'consentHandleStatus.status': {$eq: 'PENDING'}}, {'errorInfo': {$exists: false}}]}", fields = "{consentResp: 1}")
    List<ConsentRequestDetail> findRequestWithPendingConsentApproval();

    ConsentRequestDetail findByConsentHandleStatusId(String consentId);



}
