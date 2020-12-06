package com.datadynamics.bigdata.api.service.iam.util;

import com.datadynamics.bigdata.api.service.iam.model.AccessKeyStatus;
import com.datadynamics.bigdata.api.service.iam.model.http.*;
import org.apache.commons.lang3.RandomUtils;

import java.util.Date;
import java.util.List;

public class IamModelUtils {

    public static CreateUserResponse createUser(String requestId, String path, String userName, String userId, String arn) {
        CreateUserResult createUserResult = new CreateUserResult();
        com.datadynamics.bigdata.api.service.iam.model.http.User user = new com.datadynamics.bigdata.api.service.iam.model.http.User();
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
        com.datadynamics.bigdata.api.service.iam.model.http.ListGroupsResult listGroupsResult = new ListGroupsResult();
        groups.stream().forEach(group -> {
            String groupName = group.getGroupId().getGroupName();
            String path = group.getGroupId().getPath();

            Group g = new Group();
            g.setPath(path);
            g.setGroupName(groupName);
            g.setGroupId(groupName);
            g.setArn(String.format("arn:aws:iam::%s:group%s%s", RandomUtils.nextInt(), path, groupName));
            listGroupsResult.getGroups().getMembers().add(g);
        });

        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        return ListGroupsResponse.builder().listGroupsResult(listGroupsResult).responseMetadata(responseMetadata).build();
    }

    public static ListUsersResponse listUsers(String requestId, List<com.datadynamics.bigdata.api.service.iam.model.User> users) {
        ListUsersResult listUsersResult = new ListUsersResult();
        for (com.datadynamics.bigdata.api.service.iam.model.User user : users) {
            String username = user.getUserId().getUsername();
            String path = user.getUserId().getPath();

            com.datadynamics.bigdata.api.service.iam.model.http.User u = new User();
            u.setUserName(username);
            u.setUserId(username);
            u.setPath(path);
            u.setArn(String.format("arn:aws:iam::%s:user%s%s", RandomUtils.nextInt(), "/", username));
            u.setPasswordLastUsed(new Date());
            listUsersResult.getUsers().getMembers().add(u);
        }

        // 페이징 처리할 때 사용하며 이 값이 True인 경우 Marker도 지정해야 한다.
        listUsersResult.setIsTruncated(false);

        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        return ListUsersResponse.builder().listUsersResult(listUsersResult).responseMetadata(responseMetadata).build();
    }

    public static CreateAccessKeyResponse createAccessKey(String userName, String accessKey, String secretKey, AccessKeyStatus status) {
        CreateAccessKeyResult createAccessKeyResult = new CreateAccessKeyResult();
        AccessKey key = new AccessKey();
        key.setAccessKeyId(accessKey);
        key.setUserName(userName);
        key.setStatus(status.getValue());
        key.setSecretAccessKey(secretKey);
        createAccessKeyResult.setAccessKey(key);
        return CreateAccessKeyResponse.builder().createAccessKeyResult(createAccessKeyResult).build();
    }
}