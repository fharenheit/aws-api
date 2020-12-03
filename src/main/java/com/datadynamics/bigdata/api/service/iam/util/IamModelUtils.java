package com.datadynamics.bigdata.api.service.iam.util;

import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.User;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateUserResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;

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

}