package com.datadynamics.bigdata.api.service.iam.commands;

import com.datadynamics.bigdata.api.service.iam.model.AccessKeyStatus;
import com.datadynamics.bigdata.api.service.iam.model.Credential;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateAccessKeyResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.repository.CredentialRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
import com.datadynamics.bigdata.api.service.iam.util.IamModelUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CreateAccessKeyIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    /**
     * Spring Framework Application Context
     */
    private ApplicationContext applicationContext;

    private UserRepository userRepository;

    /**
     * Password Encoder
     */
    private PasswordEncoder passwordEncoder;

    private CredentialRepository credentialRepository;

    @Override
    public String getName() {
        return "CreateAccessKey";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String userName = requestParams.get("UserName");

        if (StringUtils.isEmpty(userName)) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> byId = userRepository.findById(UserId.builder().username(userName).path("/").build());
        if (!byId.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // 새로운 Secret Key를 생성한다. 이 값은 디코딩이 되지 않는다.
        String uuid = UUID.randomUUID().toString();
        String secretKey = passwordEncoder.encode(uuid);

        Optional<Credential> credentialById = credentialRepository.findById(userName);
        if (credentialById.isPresent()) {
            // Credential이 있는 경우 Status는 변경하지 않고 Secret Key만 변경한다.
            // 이 경우 어떻게 해야할지 결정해야 한다.
            Credential credential = credentialById.get();
            credential.setAccessKey(userName);
            credential.setSecretKey(secretKey);
            credential.setUuid(uuid);
            credentialRepository.save(credential);
        } else {
            // Credential이 없는 경우 신규로 생성한다.
            Credential credential = Credential.builder()
                    .accessKey(userName)
                    .secretKey(secretKey)
                    .username(userName)
                    .uuid(uuid)
                    .status(AccessKeyStatus.Active)
                    .build();
            credentialRepository.save(credential);
        }

        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        CreateAccessKeyResponse createAccessKeyResponse = IamModelUtils.createAccessKey(userName, userName, secretKey, AccessKeyStatus.Active);
        createAccessKeyResponse.setResponseMetadata(responseMetadata);
        return ResponseEntity.ok(createAccessKeyResponse);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.userRepository = applicationContext.getBean(UserRepository.class);
        this.credentialRepository = applicationContext.getBean(CredentialRepository.class);
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
}
