package com.datadynamics.bigdata.api.service.iam.commands;

import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.GroupId;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateGroupResponse;
import com.datadynamics.bigdata.api.service.iam.repository.GroupRepository;
import com.datadynamics.bigdata.api.service.iam.service.GroupService;
import com.datadynamics.bigdata.api.service.iam.util.IamModelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class CreateGroupIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    /**
     * Spring Framework Application Context
     */
    private ApplicationContext applicationContext;

    /**
     * Group Service
     */
    private GroupService groupService;

    @Override
    public String getName() {
        return "CreateGroup";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String groupName = requestParams.get("GroupName");
        String path = StringUtils.isEmpty(requestParams.get("Path")) ? "/" : UriUtils.decode(requestParams.get("Path"), Charset.defaultCharset());

        CreateGroupResponse createGroupResponse = IamModelUtils.createGroup(requestId, groupName, arn(path, groupName), null);

        GroupId groupId = GroupId.builder().groupName(groupName).path(path).build();
        Optional<Group> byId = this.groupService.getGroupById(groupId);
        if (byId.isPresent()) {
            // EntityAlreadyExists : 409
            return ResponseEntity.status(409).body(createGroupResponse);
        }

        Group group = Group.builder().groupId(groupId).build();
        this.groupService.save(group);

        createGroupResponse.getCreateGroupResult().getGroup().setCreateDate(group.getCreateTime());
        return ResponseEntity.ok(createGroupResponse);
    }

    public String arn(String path, String groupName) {
        return String.format("arn:aws:iam::%s:group%s%s", RandomUtils.nextInt(), path.endsWith("/") ? path : path + "/", groupName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.groupService = applicationContext.getBean(GroupService.class);
    }
}
