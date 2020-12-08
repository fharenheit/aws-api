package com.datadynamics.bigdata.api.service.iam.commands.user;

import com.datadynamics.bigdata.api.service.iam.commands.IamDefaultRequestCommand;
import com.datadynamics.bigdata.api.service.iam.commands.IamRequestCommand;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.http.user.ListUsersResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
import com.datadynamics.bigdata.api.service.iam.util.IamModelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class ListUsersIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    /**
     * Spring Framework Application Context
     */
    private ApplicationContext applicationContext;

    /**
     * User Repository
     */
    private UserRepository userRepository;

    @Override
    public String getName() {
        return "ListUsers";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String pathPrefix = StringUtils.isEmpty(requestParams.get("PathPrefix")) ? "/" : UriUtils.decode(requestParams.get("PathPrefix"), Charset.defaultCharset());

        Optional<List<User>> byId = this.userRepository.findUsersWithPathPrefix(pathPrefix);
        ListUsersResponse listUsersResponse = null;
        if (byId.isPresent()) {
            listUsersResponse = IamModelUtils.listUsers(requestId, byId.get());
        } else {
            listUsersResponse = getEmptyResponse(requestId);
        }
        return ResponseEntity.ok(listUsersResponse);
    }

    private ListUsersResponse getEmptyResponse(String requestId) {
        ListUsersResponse ListUsersResponse = new ListUsersResponse();
        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        ListUsersResponse.setResponseMetadata(responseMetadata);
        return ListUsersResponse;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.userRepository = applicationContext.getBean(UserRepository.class);
    }
}
