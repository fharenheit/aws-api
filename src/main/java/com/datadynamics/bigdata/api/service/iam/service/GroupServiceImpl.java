package com.datadynamics.bigdata.api.service.iam.service;

import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.GroupId;
import com.datadynamics.bigdata.api.service.iam.repository.GroupRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
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
}
