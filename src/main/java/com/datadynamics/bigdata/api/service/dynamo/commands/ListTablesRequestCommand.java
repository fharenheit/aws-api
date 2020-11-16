package com.datadynamics.bigdata.api.service.dynamo.commands;

import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class ListTablesRequestCommand extends DefaultDynamoRequestCommand {
    @Override
    public String getName() {
        return "ListTables";
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response, String body) {
        String username = getUsername(request);
        try {
            ListTablesRequest listTablesRequest = jsonMapper.readValue(body, ListTablesRequest.class);
            Integer limit = listTablesRequest.getLimit();

            ListTablesResult result = new ListTablesResult();
            String[] strings = {"a", "b"};
            result.setTableNames(Arrays.asList(strings));
            result.setLastEvaluatedTableName("b");

            return ResponseEntity.ok(jsonMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
