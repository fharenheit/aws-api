package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.iam.S3PermissionUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3PermissionUserRepository extends CrudRepository<S3PermissionUser, Long> {
}
