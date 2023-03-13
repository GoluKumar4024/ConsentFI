package com.technocrats.aa.repo;

import com.technocrats.aa.model.ConsentArtefactDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentArtefactDetailRepo extends MongoRepository<ConsentArtefactDetail, String> {

    ConsentArtefactDetail findByConsentId(String consentId);
}
