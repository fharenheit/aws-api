package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.iam.DynamoPermissionGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamoPermissionGroupRepository extends CrudRepository<DynamoPermissionGroup, Long> {
}
