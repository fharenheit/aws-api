package com.datadynamics.bigdata.api.service.iam.service;

import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.GroupId;
import com.datadynamics.bigdata.api.service.iam.model.User;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Optional<List<Group>> getGroupsWithPathPrefix(String pathPrefix);

    Optional<Group> getGroupById(GroupId groupId);

    void save(Group group);

    void addUserToGroup(User user, Group group);
}
