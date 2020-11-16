package com.datadynamics.bigdata.api.service.dynamo.commands;

import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class QueryRequestCommand extends DefaultDynamoRequestCommand {

    @Override
    public String getName() {
        return "Query";
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response, String body) {
        try {
            QueryRequest queryRequest = jsonMapper.readValue(body, QueryRequest.class);

            System.out.println(queryRequest);
            System.out.println(queryRequest.getTableName());
            System.out.println(queryRequest.getFilterExpression());
            System.out.println(queryRequest.getKeyConditionExpression());
            System.out.println(queryRequest.getExpressionAttributeValues());
            System.out.println(queryRequest.getAttributesToGet());

            QueryResult result = new QueryResult();
            return ResponseEntity.ok(jsonMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
