package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.UserToGroup;
import com.datadynamics.bigdata.api.service.iam.model.UserToGroupId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToGroupRepository extends CrudRepository<UserToGroup, UserToGroupId> {
}
