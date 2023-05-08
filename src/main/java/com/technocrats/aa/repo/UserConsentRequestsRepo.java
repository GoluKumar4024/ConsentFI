package com.technocrats.aa.repo;

import com.technocrats.aa.model.UserConsentRequests;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConsentRequestsRepo extends MongoRepository<UserConsentRequests, String> {

    @Query("{'userEmailId': {$eq: ?0}}")
    List<UserConsentRequests> findAllRequestsWithUserEmailId(String emailId);

    UserConsentRequests findByRefId(String refId);

}
