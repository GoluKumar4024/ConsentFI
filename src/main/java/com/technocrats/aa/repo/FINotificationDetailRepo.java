package com.technocrats.aa.repo;

import com.technocrats.aa.model.FINotificationDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FINotificationDetailRepo extends MongoRepository<FINotificationDetail, String> {
}
