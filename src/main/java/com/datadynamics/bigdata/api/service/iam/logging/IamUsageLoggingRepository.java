package com.datadynamics.bigdata.api.service.iam.logging;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IamUsageLoggingRepository extends CrudRepository<IamUsageLogging, Long> {
}
