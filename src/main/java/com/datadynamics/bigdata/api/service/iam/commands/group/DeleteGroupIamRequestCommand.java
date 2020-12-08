package com.datadynamics.bigdata.api.service.iam.commands.group;

import com.datadynamics.bigdata.api.service.iam.commands.IamDefaultRequestCommand;
import com.datadynamics.bigdata.api.service.iam.commands.IamRequestCommand;
import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.GroupId;
import com.datadynamics.bigdata.api.service.iam.model.UserToGroup;
import com.datadynamics.bigdata.api.service.iam.model.http.group.DeleteGroupResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.service.GroupService;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class DeleteGroupIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

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
        return "DeleteGroup";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String groupName = requestParams.get("GroupName");
        String path = StringUtils.isEmpty(requestParams.get("Path")) ? "/" : UriUtils.decode(requestParams.get("Path"), Charset.defaultCharset());

        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        DeleteGroupResponse deleteGroupResponse = new DeleteGroupResponse();
        deleteGroupResponse.setResponseMetadata(responseMetadata);

        GroupId groupId = GroupId.builder().groupName(groupName).path(path).build();
        Optional<Group> byId = this.groupService.getGroupById(groupId);
        if (!byId.isPresent()) {
            // NoSuchEntity : 404
            return ResponseEntity.notFound().build();
        }

        Optional<List<UserToGroup>> userToGroupById = this.groupService.getUserToGroup(groupId);
        if (userToGroupById.isPresent() && userToGroupById.get().size() > 1) {
            // DeleteConflict : 409 조건 성립 확인
            // 그룹에 사용자가 포함되어 있다면 조건이 성립된다.
            return ResponseEntity.status(409).build();
        }

        this.groupService.deleteUserToGroup(groupId);

        return ResponseEntity.ok(deleteGroupResponse);
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
