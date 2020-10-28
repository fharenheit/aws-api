package com.datadynamics.bigdata.api.repository;

import com.datadynamics.bigdata.api.model.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
