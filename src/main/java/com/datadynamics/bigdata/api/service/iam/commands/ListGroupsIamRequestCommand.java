package com.datadynamics.bigdata.api.service.iam.commands;

import com.datadynamics.bigdata.api.service.iam.model.Group;
import com.datadynamics.bigdata.api.service.iam.model.http.ListGroupsResponse;
import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
import com.datadynamics.bigdata.api.service.iam.repository.GroupRepository;
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
public class ListGroupsIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private GroupRepository groupRepository;

    @Override
    public String getName() {
        return "ListGroups";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception {
        String requestId = UUID.randomUUID().toString();
        Map<String, String> requestParams = parseRequestBody(body);
        String pathPrefix = StringUtils.isEmpty(requestParams.get("PathPrefix")) ? "/" : UriUtils.decode(requestParams.get("PathPrefix"), Charset.defaultCharset());

        Optional<List<Group>> byId = this.groupRepository.findGroupsWithPathPrefix(pathPrefix);
        ListGroupsResponse listGroupsResponse = null;
        if (byId.isPresent()) {
            listGroupsResponse = IamModelUtils.listGroups(requestId, byId.get());
        } else {
            listGroupsResponse = getEmptyResponse(requestId);
        }
        return ResponseEntity.ok(listGroupsResponse);
    }

    private ListGroupsResponse getEmptyResponse(String requestId) {
        ListGroupsResponse listGroupsResponse = new ListGroupsResponse();
        ResponseMetadata responseMetadata = ResponseMetadata.builder().requestId(requestId).build();
        listGroupsResponse.setResponseMetadata(responseMetadata);
        return listGroupsResponse;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.groupRepository = applicationContext.getBean(GroupRepository.class);
    }
}