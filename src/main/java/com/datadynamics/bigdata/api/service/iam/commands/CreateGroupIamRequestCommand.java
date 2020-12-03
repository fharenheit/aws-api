package com.datadynamics.bigdata.api.service.iam.commands;

import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateGroupResponse;
import com.datadynamics.bigdata.api.service.iam.repository.GroupRepository;
import com.datadynamics.bigdata.api.service.iam.util.IamModelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class CreateGroupIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private GroupRepository groupRepository;

    @Override
    public String getName() {
        return "CreateGroup";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String groupName = requestParams.get("GroupName");

        CreateGroupResponse createGroupResponse = IamModelUtils.createGroup(requestId, groupName, arn(groupName), null);

        Optional<Group> byId = this.groupRepository.findById(groupName);
        if (byId.isPresent()) {
            // EntityAlreadyExists : 409
            return ResponseEntity.status(409).body(createGroupResponse);
        }

        Group group = Group.builder().groupName(groupName).build();
        this.groupRepository.save(group);

        createGroupResponse.getCreateGroupResult().getGroup().setCreateDate(group.getCreateTime());
        return ResponseEntity.ok(createGroupResponse);
    }

    public String arn(String groupName) {
        return String.format("arn:aws:iam::%s:group/%s", RandomUtils.nextInt(), groupName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.groupRepository = applicationContext.getBean(GroupRepository.class);
    }
}
