package com.datadynamics.bigdata.api.service.dynamo.commands;

import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.TableStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DescribeTableRequestCommand extends DefaultDynamoRequestCommand {

    @Override
    public String getName() {
        return "DescribeTable";
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response, String body) {
        String username = getUsername(request);
        try {
            DescribeTableRequest describeTableRequest = jsonMapper.readValue(body, DescribeTableRequest.class);
            String tableName = describeTableRequest.getTableName();

            DescribeTableResult result = new DescribeTableResult();
            TableDescription description = new TableDescription();
            description.setTableId("1");
            description.setTableName(tableName);
            description.setItemCount(1000L);
            description.setTableSizeBytes(1000L);
            description.setTableStatus(TableStatus.ACTIVE);
            result.setTable(description);

            return ResponseEntity.ok(jsonMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
