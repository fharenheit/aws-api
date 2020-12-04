package com.datadynamics.bigdata.api.service.iam.commands;

import com.amazonaws.services.identitymanagement.model.GetUserResult;
import com.datadynamics.bigdata.api.service.iam.model.Credential;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.model.http.GetUserResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.repository.CredentialRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
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

public class GetUserIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private UserRepository userRepository;

    private CredentialRepository credentialRepository;

    @Override
    public String getName() {
        return "GetUser";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String userName = requestParams.get("UserName");

        if (StringUtils.isEmpty(userName)) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> byId = userRepository.findById(UserId.builder().path("/").username(userName).build());
        if (!byId.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Credential> credentialById = credentialRepository.findById(userName);

        GetUserResult getUserResult = new GetUserResult();
        if (credentialById.isPresent()) {
            com.amazonaws.services.identitymanagement.model.User user = new com.amazonaws.services.identitymanagement.model.User();
            user.setUserId(byId.get().getUserId().getUsername());
            user.setPath(byId.get().getUserId().getPath());
            user.setArn(CreateUserIamRequestCommand.arn(byId.get().getUserId().getPath(), byId.get().getUserId().getUsername()));
            user.setCreateDate(byId.get().getCreateTime());
            user.setPasswordLastUsed(byId.get().getCreateTime());
            getUserResult.setUser(user);
        }

        GetUserResponse res = GetUserResponse.builder()
                .getUserResult(getUserResult)
                .responseMetadata(ResponseMetadata.builder().requestId(requestId).build())
                .build();
        return ResponseEntity.ok(res);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.userRepository = applicationContext.getBean(UserRepository.class);
        this.credentialRepository = applicationContext.getBean(CredentialRepository.class);
    }
}
