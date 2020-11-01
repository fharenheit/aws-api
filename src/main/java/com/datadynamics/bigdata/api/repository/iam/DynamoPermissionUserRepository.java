package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.iam.DynamoPermissionUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamoPermissionUserRepository extends CrudRepository<DynamoPermissionUser, Long> {
}
