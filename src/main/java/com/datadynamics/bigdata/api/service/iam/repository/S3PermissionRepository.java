package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.S3Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3PermissionRepository extends CrudRepository<S3Permission, Long> {
}
