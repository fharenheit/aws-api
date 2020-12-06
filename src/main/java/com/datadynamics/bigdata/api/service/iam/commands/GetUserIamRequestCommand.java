package com.datadynamics.bigdata.api.service.iam.commands;

import com.datadynamics.bigdata.api.service.iam.model.Credential;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.model.http.GetUserResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.GetUserResult;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * IAM GetUser Action
 */
public class GetUserIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    /**
     * Spring Framework Application Context
     */
    private ApplicationContext applicationContext;

    /**
     * User Service
     */
    private UserService userService;

    @Override
    public String getName() {
        return "GetUser";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String userName = requestParams.get("UserName");

        // 사용자명이 Request에 들어오지 않는 경우 404를 리턴한다.
        if (StringUtils.isEmpty(userName)) {
            return ResponseEntity.notFound().build();
        }

        // Request의 사용자가 실제로 존재하지 않으면 404를 리턴한다.
        Optional<User> byId = userService.getUserByUserId(UserId.builder().path("/").username(userName).build());
        if (!byId.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Credential> credentialById = userService.getCredentialByUserId(userName);

        GetUserResult getUserResult = new GetUserResult();
        if (credentialById.isPresent()) {
            // 이미 Access Key가 등록된 적이 있다면 조회한 정보를 그대로 리턴한다.
            com.datadynamics.bigdata.api.service.iam.model.http.User user = new com.datadynamics.bigdata.api.service.iam.model.http.User();
            user.setUserId(byId.get().getUserId().getUsername());
            user.setUserName(byId.get().getUserId().getUsername());
            user.setPath(byId.get().getUserId().getPath());
            user.setArn(CreateUserIamRequestCommand.arn(byId.get().getUserId().getPath(), byId.get().getUserId().getUsername()));
            user.setCreateDate(byId.get().getCreateTime());
            user.setPasswordLastUsed(byId.get().getCreateTime());
            getUserResult.setUser(user);
        }

        // 사용자가 없는 경우 사용자 정보는 없고 Response만 구성한다.
        GetUserResponse res = GetUserResponse.builder()
                .getUserResult(getUserResult)
                .responseMetadata(ResponseMetadata.builder().requestId(requestId).build())
                .build();
        return ResponseEntity.ok(res);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.userService = applicationContext.getBean(UserService.class);
    }
}
