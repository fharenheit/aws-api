package com.datadynamics.bigdata.api.service.dynamo.commands;

import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class ListTablesRequestCommand extends DefaultDynamoRequestCommand {
    @Override
    public String getName() {
        return "ListTables";
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response, String body) {
        response.setContentType("application/x-amz-json-1.0");
        response.setHeader("x-amzn-RequestId", UUID.randomUUID().toString());

        String username = getUsername(request);

        ListTablesResult result = new ListTablesResult();
        String[] strings = {"a", "b"};
        result.setTableNames(Arrays.asList(strings));
        result.setLastEvaluatedTableName("b");
        return ResponseEntity.ok(result);
    }
}
