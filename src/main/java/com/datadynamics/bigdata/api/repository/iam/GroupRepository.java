package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, String> {
}
