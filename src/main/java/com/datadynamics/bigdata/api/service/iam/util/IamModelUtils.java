package com.datadynamics.bigdata.api.service.iam.util;

import com.amazonaws.services.identitymanagement.model.*;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateGroupResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateUserResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ListGroupsResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import org.apache.commons.lang3.RandomUtils;

import java.util.Date;
import java.util.List;

public class IamModelUtils {

    public static CreateUserResponse createUser(String requestId, String path, String userName, String userId, String arn) {
        CreateUserResult createUserResult = new CreateUserResult();
        User user = new User();
        user.setArn(arn);
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPath(path);
        createUserResult.setUser(user);

        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        return CreateUserResponse.builder().createUserResult(createUserResult).responseMetadata(responseMetadata).build();
    }

    public static CreateGroupResponse createGroup(String requestId, String groupName, String arn, Date createDate) {
        CreateGroupResult createGroupResult = new CreateGroupResult();
        Group group = new Group();
        group.setArn(arn);
        if (createDate != null) group.setCreateDate(createDate);
        group.setGroupId(groupName);
        group.setGroupName(groupName);
        group.setPath("/");
        createGroupResult.setGroup(group);

        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        return CreateGroupResponse.builder().createGroupResult(createGroupResult).responseMetadata(responseMetadata).build();
    }

    public static ListGroupsResponse listGroups(String requestId, List<com.datadynamics.bigdata.api.service.iam.model.Group> groups) {
        ListGroupsResult listGroupsResult = new ListGroupsResult();
        groups.stream().forEach(group -> {
            String groupName = group.getGroupId().getGroupName();
            String path = group.getGroupId().getPath();

            Group g = new Group();
            g.setPath(path);
            g.setGroupName(groupName);
            g.setGroupId(groupName);
            g.setArn(String.format("arn:aws:iam::%s:group%s%s", RandomUtils.nextInt(), path, groupName));
            g.setCreateDate(group.getCreateTime());
            listGroupsResult.getGroups().add(g);
        });

        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        return ListGroupsResponse.builder().listGroupsResult(listGroupsResult).responseMetadata(responseMetadata).build();
    }
}