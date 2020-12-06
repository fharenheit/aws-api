package com.datadynamics.bigdata.api.service.iam.commands;

import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.GroupId;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.model.http.AddUserToGroupResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.service.GroupService;
import com.datadynamics.bigdata.api.service.iam.service.UserService;
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
public class AddUserToGroupIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private GroupService groupService;

    private UserService userService;

    @Override
    public String getName() {
        return "AddUserToGroup";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);

        String groupName = requestParams.get("GroupName");
        String groupPath = StringUtils.isEmpty(requestParams.get("Path")) ? "/" : UriUtils.decode(requestParams.get("Path"), Charset.defaultCharset());

        String userName = requestParams.get("UserName");
        String userPath = StringUtils.isEmpty(requestParams.get("Path")) ? "/" : UriUtils.decode(requestParams.get("Path"), Charset.defaultCharset());

        AddUserToGroupResponse addUserToGroupResponse =
                AddUserToGroupResponse.builder().responseMetadata(ResponseMetadata.builder().requestId(requestId).build()).build();

        GroupId groupId = GroupId.builder().groupName(groupName).path(groupPath).build();
        Optional<Group> groupById = this.groupService.getGroupById(groupId);
        if (!groupById.isPresent()) {
            // NoSuchEntity : 404
            return ResponseEntity.status(404).body(addUserToGroupResponse);
        }

        UserId userId = UserId.builder().username(userName).path(userPath).build();
        Optional<User> userById = this.userService.getUserByUserId(userId);
        if (!userById.isPresent()) {
            // NoSuchEntity : 404
            return ResponseEntity.status(404).body(addUserToGroupResponse);
        }

        try {
            groupService.addUserToGroup(userById.get(), groupById.get());
        } catch (Exception e) {
            // ServiceFailure : 500
            return ResponseEntity.status(500).body(addUserToGroupResponse);
        }

        return ResponseEntity.ok(addUserToGroupResponse);
    }

    public String arn(String path, String groupName) {
        return String.format("arn:aws:iam::%s:group%s%s", RandomUtils.nextInt(), path.endsWith("/") ? path : path + "/", groupName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.groupService = applicationContext.getBean(GroupService.class);
        this.userService = applicationContext.getBean(UserService.class);
    }
}
