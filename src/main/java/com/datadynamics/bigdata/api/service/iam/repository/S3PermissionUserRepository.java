package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.S3PermissionUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3PermissionUserRepository extends CrudRepository<S3PermissionUser, Long> {
}
