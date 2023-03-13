package com.technocrats.aa.repo;

import com.technocrats.aa.model.DataFetchRequestDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataFetchRequestDetailRepo extends MongoRepository<DataFetchRequestDetail,String> {
    DataFetchRequestDetail findBySessionDetailsSessionId(String sessionId);
}
