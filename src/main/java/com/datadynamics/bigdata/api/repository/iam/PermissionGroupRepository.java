package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.PermissionGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends CrudRepository<PermissionGroup, Long> {
}
