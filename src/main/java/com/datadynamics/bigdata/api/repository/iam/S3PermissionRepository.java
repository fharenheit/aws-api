package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.S3Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3PermissionRepository extends CrudRepository<S3Permission, Long> {
}
