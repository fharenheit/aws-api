package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.model.iam.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, String> {
}
