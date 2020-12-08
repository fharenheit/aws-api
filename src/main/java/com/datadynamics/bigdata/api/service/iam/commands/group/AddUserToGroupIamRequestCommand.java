package com.datadynamics.bigdata.api.service.iam.commands.group;

import com.datadynamics.bigdata.api.service.iam.commands.IamDefaultRequestCommand;
import com.datadynamics.bigdata.api.service.iam.commands.IamRequestCommand;
import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.GroupId;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.model.http.group.AddUserToGroupResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.service.GroupService;
import com.datadynamics.bigdata.api.service.iam.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

    /**
     * Spring Framework Application Context
     */
    private ApplicationContext applicationContext;

    /**
     * Group Service
     */
    private GroupService groupService;

    /**
     * User Service
     */
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
        // 클라이언트 API에서는 Path를 설정할 수 없으며 내부에서는 Path를 사용해야 한다.
        String groupPath = StringUtils.isEmpty(requestParams.get("Path")) ? "/" : UriUtils.decode(requestParams.get("Path"), Charset.defaultCharset());

        String userName = requestParams.get("UserName");
        // 클라이언트 API에서는 Path를 설정할 수 없으며 내부에서는 Path를 사용해야 한다.
        String userPath = StringUtils.isEmpty(requestParams.get("Path")) ? "/" : UriUtils.decode(requestParams.get("Path"), Charset.defaultCharset());

        AddUserToGroupResponse addUserToGroupResponse =
                AddUserToGroupResponse.builder().responseMetadata(ResponseMetadata.builder().requestId(requestId).build()).build();

        GroupId groupId = GroupId.builder().groupName(groupName).path(groupPath).build();
        Optional<Group> groupById = this.groupService.getGroupById(groupId);
        if (!groupById.isPresent()) {
            // 사용자가 없다면 에러를 보낸다.
            // NoSuchEntity : 404
            return ResponseEntity.status(404).body(addUserToGroupResponse);
        }

        UserId userId = UserId.builder().username(userName).path(userPath).build();
        Optional<User> userById = this.userService.getUserByUserId(userId);
        if (!userById.isPresent()) {
            // 사용자가 없다면 에러를 보낸다.
            // NoSuchEntity : 404
            return ResponseEntity.status(404).body(addUserToGroupResponse);
        }

        try {
            groupService.addUserToGroup(userById.get(), groupById.get());
        } catch (Exception e) {
            // 이미 매핑을 했다면 더이상 매핑할 수 없으므로 에러 메시지를 발송한다.
            // 그런데 규격에서 이미 매핑이 되어 있는 경우 OK인지 FAIL인지 명확하지 않다.
            // ServiceFailure : 500
            return ResponseEntity.status(500).body(addUserToGroupResponse);
        }

        return ResponseEntity.ok(addUserToGroupResponse);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.groupService = applicationContext.getBean(GroupService.class);
        this.userService = applicationContext.getBean(UserService.class);
    }
}
