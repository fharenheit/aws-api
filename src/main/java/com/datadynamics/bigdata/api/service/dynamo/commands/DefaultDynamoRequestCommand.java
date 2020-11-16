package com.datadynamics.bigdata.api.service.dynamo.commands;

import com.amazonaws.services.dynamodbv2.local.shared.mapper.DynamoDBObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class DefaultDynamoRequestCommand implements DynamoRequestCommand {

    public static DynamoDBObjectMapper jsonMapper = new DynamoDBObjectMapper();

    public String getUsername(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return StringUtils.substringBetween(authorization, "Credential=", "/");
    }
}
