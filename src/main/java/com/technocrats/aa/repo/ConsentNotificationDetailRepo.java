package com.technocrats.aa.repo;

import com.technocrats.aa.model.ConsentNotificationDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentNotificationDetailRepo extends MongoRepository<ConsentNotificationDetail, String> {
}
