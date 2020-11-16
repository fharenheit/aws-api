package com.datadynamics.bigdata.api.service.dynamo.commands;

import com.amazonaws.services.dynamodbv2.local.shared.mapper.DynamoDBObjectMapper;

public abstract class DefaultDynamoRequestCommand implements DynamoRequestCommand {

    public static DynamoDBObjectMapper jsonMapper = new DynamoDBObjectMapper();

}
