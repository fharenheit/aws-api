package com.datadynamics.bigdata.api.service.s3.logging;

import com.datadynamics.bigdata.api.model.iam.DynamoPermission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3UsageLoggingRepository extends CrudRepository<S3UsageLogging, Long> {
}
