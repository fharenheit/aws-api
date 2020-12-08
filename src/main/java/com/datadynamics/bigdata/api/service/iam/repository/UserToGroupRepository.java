package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.UserToGroup;
import com.datadynamics.bigdata.api.service.iam.model.UserToGroupId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserToGroupRepository extends CrudRepository<UserToGroup, UserToGroupId> {

    @Query("select g from com.datadynamics.bigdata.api.service.iam.model.UserToGroup g where g.userToGroupId.groupPath = :groupPath and g.userToGroupId.groupName = :groupName")
    Optional<List<UserToGroup>> findUserToGroupByGroupPathAndGroupName(
            @Param("groupPath") String groupPath,
            @Param("groupName") String groupName);

    @Transactional
    @Modifying
    @Query("delete from com.datadynamics.bigdata.api.service.iam.model.UserToGroup g where g.userToGroupId.groupPath = :groupPath and g.userToGroupId.groupName = :groupName")
    void deleteUserToGroup(@Param("groupPath") String groupPath,
                           @Param("groupName") String groupName);
}