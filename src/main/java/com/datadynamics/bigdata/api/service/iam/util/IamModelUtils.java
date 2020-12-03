package com.datadynamics.bigdata.api.service.iam.util;

import com.amazonaws.services.identitymanagement.model.CreateGroupResult;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.Group;
import com.amazonaws.services.identitymanagement.model.User;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateGroupResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateUserResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;

import java.util.Date;

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
}