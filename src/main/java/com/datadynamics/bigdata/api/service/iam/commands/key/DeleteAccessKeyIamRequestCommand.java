package com.datadynamics.bigdata.api.service.iam.commands.key;

import com.datadynamics.bigdata.api.service.iam.commands.IamDefaultRequestCommand;
import com.datadynamics.bigdata.api.service.iam.commands.IamRequestCommand;
import com.datadynamics.bigdata.api.service.iam.model.Credential;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.model.http.key.DeleteAccessKeyResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.repository.CredentialRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class DeleteAccessKeyIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    /**
     * Spring Framework Application Context
     */
    private ApplicationContext applicationContext;

    /**
     * User Repository
     */
    private UserRepository userRepository;

    /**
     * Credential Repository
     */
    private CredentialRepository credentialRepository;

    @Override
    public String getName() {
        return "DeleteAccessKey";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String userName = requestParams.get("UserName");
        String accessKeyId = requestParams.get("AccessKeyId");

        if (StringUtils.isEmpty(userName)) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> byId = userRepository.findById(UserId.builder().username(userName).path("/").build());
        if (!byId.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Response를 생성한다.
        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        DeleteAccessKeyResponse deleteAccessKeyResponse = new DeleteAccessKeyResponse();
        deleteAccessKeyResponse.setResponseMetadata(responseMetadata);

        Optional<Credential> credentialById = credentialRepository.findCredentialByUsernameAndAccessKey(userName, accessKeyId);
        if (credentialById.isPresent()) {
            // Username과 Access Key로 사용자를 찾은 후 삭제한다.
            credentialRepository.delete(credentialById.get());
        } else {
            // NoSuchEntity : 404
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deleteAccessKeyResponse);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.userRepository = applicationContext.getBean(UserRepository.class);
        this.credentialRepository = applicationContext.getBean(CredentialRepository.class);
    }
}
