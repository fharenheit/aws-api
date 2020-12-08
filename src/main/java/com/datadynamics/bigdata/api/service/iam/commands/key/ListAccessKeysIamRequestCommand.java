package com.datadynamics.bigdata.api.service.iam.commands.key;

import com.datadynamics.bigdata.api.service.iam.commands.IamDefaultRequestCommand;
import com.datadynamics.bigdata.api.service.iam.commands.IamRequestCommand;
import com.datadynamics.bigdata.api.service.iam.model.Credential;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.model.http.*;
import com.datadynamics.bigdata.api.service.iam.model.http.key.AccessKeyMetadata;
import com.datadynamics.bigdata.api.service.iam.model.http.key.ListAccessKeysResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.key.ListAccessKeysResult;
import com.datadynamics.bigdata.api.service.iam.model.http.user.Member;
import com.datadynamics.bigdata.api.service.iam.repository.CredentialRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ListAccessKeysIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    /**
     * Spring Framework Application Context
     */
    private ApplicationContext applicationContext;

    /**
     * User Repository
     */
    private UserRepository userRepository;

    private CredentialRepository credentialRepository;

    @Override
    public String getName() {
        return "ListAccessKeys";
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
        List<Member> members = new ArrayList();
        if (credentialById.isPresent()) {
            members.add(Member.builder()
                    .status(credentialById.get().getStatus().getValue())
                    .accessKeyId(credentialById.get().getAccessKey())
                    .userName(credentialById.get().getUsername())
                    .createDate(new Date())
                    .build());
        }

        ListAccessKeysResult listAccessKeysResult = ListAccessKeysResult.builder()
                .accessKeyMetadata(AccessKeyMetadata.builder().members(members).build())
                .userName(credentialById.get().getUsername())
                .isTruncated(false)
                .build();

        ListAccessKeysResponse res = ListAccessKeysResponse.builder()
                .listAccessKeysResult(listAccessKeysResult)
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
