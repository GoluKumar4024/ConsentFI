package com.technocrats.aa.repo;

import com.technocrats.aa.model.NotificationTokens;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTokensRepo extends MongoRepository<NotificationTokens, String> {
    NotificationTokens findByEmailId(String emailId);
}
