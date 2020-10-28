package com.datadynamics.bigdata.api.repository;

import com.datadynamics.bigdata.api.model.PermissionUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionUserRepository extends CrudRepository<PermissionUser, Long> {
}
