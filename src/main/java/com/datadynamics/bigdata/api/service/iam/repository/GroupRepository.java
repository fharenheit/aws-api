package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.GroupId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, GroupId> {

    @Query("select u from com.datadynamics.bigdata.api.service.iam.model.Group u where u.groupId.path LIKE CONCAT('',:pathPrefix,'%')")
    Optional<List<Group>> findGroupsWithPathPrefix(@Param("pathPrefix") String pathPrefix);

}
