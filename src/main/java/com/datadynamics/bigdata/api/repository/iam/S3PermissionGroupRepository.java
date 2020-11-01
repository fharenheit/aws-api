package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.iam.S3PermissionGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3PermissionGroupRepository extends CrudRepository<S3PermissionGroup, Long> {
}
