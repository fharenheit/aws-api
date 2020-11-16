package com.amazonaws.services.dynamodbv2.parser;

import com.amazonaws.services.dynamodbv2.datamodel.DocumentFactory;
import com.amazonaws.services.dynamodbv2.datamodel.DocumentNode;
import com.amazonaws.services.dynamodbv2.exceptions.AWSExceptionFactory;
import com.amazonaws.services.dynamodbv2.local.shared.dataaccess.LocalDocumentFactory;
import com.amazonaws.services.dynamodbv2.local.shared.env.LocalDBEnv;
import com.amazonaws.services.dynamodbv2.local.shared.validate.ErrorFactory;
import com.amazonaws.services.dynamodbv2.local.shared.validate.RangeQueryExpressionsWrapper;
import com.amazonaws.services.dynamodbv2.rr.ExpressionWrapper;
import com.amazonaws.services.dynamodbv2.rr.ProjectionExpressionWrapper;

import java.util.Map;

public class DynamoRequestConverter {

    protected final boolean isDocumentSupportEnabled = true;

    private ErrorFactory errorFactory = new AWSExceptionFactory();

    private final int maxExpressionSubstitutionMapSize = 999999;

    private DocumentFactory documentFactory = new LocalDocumentFactory();

    private LocalDBEnv dbEnv = new LocalDBEnv();

    private final int maxKeyAttributeNameSize = 999999;

    public RangeQueryExpressionsWrapper externalToInternalExpressions(String filterExpressionString,
                                                                      String projectionExpressionString,
                                                                      String keyConditionExpressionString,
                                                                      Map<String, String> expressionAttributeNames,
                                                                      Map<String, com.amazonaws.services.dynamodbv2.model.AttributeValue> expressionAttributeValues) {

        RangeQueryExpressionsWrapper expressionsWrapper = null;
/*
        if ((filterExpressionString != null || projectionExpressionString != null || keyConditionExpressionString != null) && this.isDocumentSupportEnabled) {
            Map<String, DocumentNode> internalExpressionAttributeValues = this.externalToInternalExpressionAttributeValues(expressionAttributeValues);
            expressionsWrapper = new RangeQueryExpressionsWrapper();
            ExpressionWrapper keyConditionExpressionWrapper;
            if (filterExpressionString != null) {
                keyConditionExpressionWrapper = this.parseFilterExpression(filterExpressionString, expressionAttributeNames, internalExpressionAttributeValues);
                expressionsWrapper.setFilterExpressionWrapper(keyConditionExpressionWrapper);
            }

            if (projectionExpressionString != null) {
                ProjectionExpressionWrapper projectionExpressionWrapper = this.parseProjectionExpression(projectionExpressionString, expressionAttributeNames);
                expressionsWrapper.setProjectionExpressionWrapper(projectionExpressionWrapper);
            }

            if (keyConditionExpressionString != null) {
                keyConditionExpressionWrapper = this.parseKeyConditionExpression(keyConditionExpressionString, expressionAttributeNames, internalExpressionAttributeValues);
                expressionsWrapper.setKeyConditionExpressionWrapper(keyConditionExpressionWrapper);
            }
        }
*/

        return expressionsWrapper;
    }

    private ExpressionWrapper parseFilterExpression(String filterExpressionString, Map<String, String> expressionAttributeNames, Map<String, DocumentNode> expressionAttributeValues) {
        ExpressionWrapper filterExpression = null;
        if (filterExpressionString != null && this.isDocumentSupportEnabled) {
            try {
                filterExpression = DynamoDbParserExt.parseExpression(filterExpressionString, expressionAttributeNames, expressionAttributeValues, this.dbEnv, this.documentFactory);
            } catch (Exception var6) {
                this.errorFactory.INVALID_FILTER_EXPRESSION.throwAsException(var6.getMessage());
            }
        }

        return filterExpression;
    }

    private ExpressionWrapper parseConditionExpression(String conditionExpressionString, Map<String, String> expressionAttributeNames, Map<String, DocumentNode> expressionAttributeValues) {
        ExpressionWrapper conditionExpression = null;
        if (conditionExpressionString != null && this.isDocumentSupportEnabled) {
            try {
                conditionExpression = DynamoDbParserExt.parseExpression(conditionExpressionString, expressionAttributeNames, expressionAttributeValues, this.dbEnv, this.documentFactory);
            } catch (Exception var6) {
                this.errorFactory.INVALID_CONDITION_EXPRESSION.throwAsException(var6.getMessage());
            }
        }

        return conditionExpression;
    }

    private ProjectionExpressionWrapper parseProjectionExpression(String projectionExpressionString, Map<String, String> expressionAttributeNames) {
        ProjectionExpressionWrapper projectionExpression = null;
        if (projectionExpressionString != null && this.isDocumentSupportEnabled) {
            try {
                projectionExpression = DynamoDbParserExt.parseProjectionExpression(projectionExpressionString, expressionAttributeNames, this.dbEnv, this.documentFactory);
            } catch (Exception var5) {
                this.errorFactory.INVALID_PROJECTION_EXPRESSION.throwAsException(var5.getMessage());
            }
        }

        return projectionExpression;
    }

    private ExpressionWrapper parseKeyConditionExpression(String keyConditionExpressionString, Map<String, String> expressionAttributeNames, Map<String, DocumentNode> expressionAttributeValues) {
        ExpressionWrapper keyConditionExpression = null;
        if (keyConditionExpressionString != null && this.isDocumentSupportEnabled) {
            try {
                keyConditionExpression = DynamoDbParserExt.parseExpression(keyConditionExpressionString, expressionAttributeNames, expressionAttributeValues, this.dbEnv, this.documentFactory);
            } catch (Exception var6) {
                this.errorFactory.INVALID_KEY_CONDITION_EXPRESSION.throwAsException(var6.getMessage());
            }
        }

        return keyConditionExpression;
    }
}