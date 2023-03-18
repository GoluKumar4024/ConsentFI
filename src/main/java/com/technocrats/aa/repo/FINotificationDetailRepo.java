package com.technocrats.aa.repo;

import com.technocrats.aa.model.FIFetchDetail;
import com.technocrats.aa.model.FINotificationDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FINotificationDetailRepo extends MongoRepository<FINotificationDetail, String> {


}
