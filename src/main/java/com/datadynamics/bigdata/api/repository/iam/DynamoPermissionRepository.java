package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.DynamoPermission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamoPermissionRepository extends CrudRepository<DynamoPermission, Long> {
}
