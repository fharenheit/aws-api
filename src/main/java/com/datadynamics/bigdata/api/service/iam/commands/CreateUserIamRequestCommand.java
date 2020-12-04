package com.datadynamics.bigdata.api.service.iam.commands;

import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.model.http.CreateUserResponse;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
import com.datadynamics.bigdata.api.service.iam.util.IamModelUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class CreateUserIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private UserRepository userRepository;

    private ObjectMapper objectMapper;

    @Override
    public String getName() {
        return "CreateUser";
    }

    // Action=CreateUser&Version=2010-05-08
    // &Path=%2Fdivision_abc%2Fsubdivision_xyz%2F
    // &UserName=helloworld
    // &PermissionsBoundary=blahblahblahblahblah&
    // Tags.member.1.Key=name&Tags.member.1.Value=hong&Tags.member.2.Key=depart&Tags.member.2.Value=C101

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String userName = requestParams.get("UserName");
        String path = StringUtils.isEmpty(requestParams.get("Path")) ? "/" : UriUtils.decode(requestParams.get("Path"), Charset.defaultCharset());
        String permissionsBoundary = requestParams.get("PermissionsBoundary"); // 실체 파악 필요

        CreateUserResponse createUserResponse = IamModelUtils.createUser(requestId, path, userName, userName, arn(path, userName));

        // 새로운 사용자를 추가하지만 Access Key와 Secret Key는 별도 Action으로 처리가 되므로 여기에서는 사용자만 추가한다.
        // Permission Boundary에 대해서 연구가 필요하다.
        Map<String, String> tags = tags(requestParams);
        UserId userId = UserId.builder().username(userName).path(path).build();
        try {
            Optional<User> byId = this.userRepository.findById(userId);
            if (byId.isPresent()) {
                // EntityAlreadyExists : 409
                return ResponseEntity.status(409).body(createUserResponse);
            }
            User user = User.builder()
                    .tags(json(tags))
                    .userId(userId)
                    .permissionBoundary(permissionsBoundary)
                    .createTime(new Timestamp(System.currentTimeMillis()))
                    .build();
            this.userRepository.save(user);
        } catch (IOException e) {
            // InvalidInput : 409
            return ResponseEntity.badRequest().body(createUserResponse);
        }

        return ResponseEntity.ok(createUserResponse);
    }

    public String arn(String path, String username) {
        return String.format("arn:aws:iam::%s:user%s/%s", RandomUtils.nextInt(), path, username);
    }

    public Map<String, String> tags(Map<String, String> requestParams) {
        Map<String, String> tags = new HashMap();
        requestParams.keySet().forEach(key -> {
            if (key.startsWith("Tags.member")) {
                tags.put(key, requestParams.get(key));
            }
        });
        return tags;
    }

    public String json(Map<String, String> tags) throws IOException {
        Map<String, String> jsonMap = new HashMap();
        int lastIndex = tags.size() / 2;
        for (int i = 1; i <= lastIndex; i++) {
            String key = tags.get(String.format("Tag.member.%s.Key", i));
            String value = tags.get(String.format("Tag.member.%s.Value", i));

            jsonMap.put(key, value);
        }
        return this.objectMapper.writeValueAsString(jsonMap);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.userRepository = applicationContext.getBean(UserRepository.class);
        this.objectMapper = (ObjectMapper) applicationContext.getBean("mapper");
    }
}
