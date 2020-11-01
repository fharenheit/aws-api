package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.DynamoPermissionGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamoPermissionGroupRepository extends CrudRepository<DynamoPermissionGroup, Long> {
}
