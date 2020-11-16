package com.datadynamics.bigdata.api.service.dynamo.commands;

import com.amazonaws.services.dynamodbv2.datamodel.Expression;
import com.amazonaws.services.dynamodbv2.datamodel.ProjectionExpression;
import com.amazonaws.services.dynamodbv2.local.shared.validate.RangeQueryExpressionsWrapper;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.rr.ExpressionWrapper;
import com.amazonaws.services.dynamodbv2.rr.ProjectionExpressionWrapper;
import com.amazonaws.services.dynamodbv2.parser.DynamoRequestConverter;
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
        response.setContentType("application/x-amz-json-1.0");


        try {
            QueryRequest queryRequest = jsonMapper.readValue(body, QueryRequest.class);

            DynamoRequestConverter inputConverter = new DynamoRequestConverter();
            RangeQueryExpressionsWrapper rangeQueryExpressionsWrapper = inputConverter.externalToInternalExpressions(queryRequest.getFilterExpression(), queryRequest.getProjectionExpression(), queryRequest.getKeyConditionExpression(), queryRequest.getExpressionAttributeNames(), queryRequest.getExpressionAttributeValues());
            ExpressionWrapper filterExpressionWrapper = rangeQueryExpressionsWrapper == null ? null : rangeQueryExpressionsWrapper.getFilterExpressionWrapper();
            ProjectionExpressionWrapper projectionExpressionWrapper = rangeQueryExpressionsWrapper == null ? null : rangeQueryExpressionsWrapper.getProjectionExpressionWrapper();
            ExpressionWrapper keyConditionExpressionWrapper = rangeQueryExpressionsWrapper == null ? null : rangeQueryExpressionsWrapper.getKeyConditionExpressionWrapper();
            Expression filterExpression = filterExpressionWrapper == null ? null : filterExpressionWrapper.getExpression();
            ProjectionExpression projectionExpression = projectionExpressionWrapper == null ? null : projectionExpressionWrapper.getProjection();

            System.out.println(queryRequest);
            System.out.println(queryRequest.getTableName());
            System.out.println(queryRequest.getFilterExpression());
            System.out.println(queryRequest.getKeyConditionExpression());
            System.out.println(queryRequest.getExpressionAttributeValues());

            System.out.println(queryRequest.getAttributesToGet());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new QueryResult());
    }

}
