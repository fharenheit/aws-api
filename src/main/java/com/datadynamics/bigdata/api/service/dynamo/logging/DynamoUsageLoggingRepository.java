package com.datadynamics.bigdata.api.service.dynamo.logging;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamoUsageLoggingRepository extends CrudRepository<DynamoUsageLogging, Long> {
}
