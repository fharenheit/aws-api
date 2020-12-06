package com.datadynamics.bigdata.api.service.iam.service;

import com.amazonaws.services.identitymanagement.model.EntityAlreadyExistsException;
import com.datadynamics.bigdata.api.service.iam.model.*;
import com.datadynamics.bigdata.api.service.iam.repository.GroupRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserToGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserToGroupRepository userToGroupRepository;

    @Override
    public Optional<List<Group>> getGroupsWithPathPrefix(String pathPrefix) {
        return groupRepository.findGroupsWithPathPrefix(pathPrefix);
    }

    @Override
    public Optional<Group> getGroupById(GroupId groupId) {
        return groupRepository.findById(groupId);
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void addUserToGroup(User user, Group group) {
        UserToGroupId id = UserToGroupId.builder()
                .userName(user.getUserId().getUsername()).userPath(user.getUserId().getPath())
                .groupName(group.getGroupId().getGroupName()).groupPath(group.getGroupId().getPath())
                .build();

        Optional<UserToGroup> utgById = userToGroupRepository.findById(id);
        if (utgById.isPresent()) {
            throw new EntityAlreadyExistsException(utgById.toString());
        }
        UserToGroup utg = UserToGroup.builder().userToGroupId(id).build();
        userToGroupRepository.save(utg);
    }
}
