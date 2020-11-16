package com.amazonaws.services.dynamodbv2.parser;

import com.amazon.dynamodb.grammar.DynamoDbExpressionParser;
import com.amazon.dynamodb.grammar.exceptions.RedundantParenthesesException;
import com.amazonaws.services.dynamodbv2.datamodel.*;
import com.amazonaws.services.dynamodbv2.dbenv.DbConfig;
import com.amazonaws.services.dynamodbv2.dbenv.DbEnv;
import com.amazonaws.services.dynamodbv2.dbenv.DbValidationError;
import com.amazonaws.services.dynamodbv2.rr.ExpressionWrapper;
import com.amazonaws.services.dynamodbv2.rr.ProjectionExpressionWrapper;
import com.amazonaws.services.dynamodbv2.rr.UpdateExpressionWrapper;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamoDbParserExt {

    public DynamoDbParserExt() {
    }

    public static ProjectionExpressionWrapper parseProjectionExpression(String string, Map<String, String> attributeNameParameters, DbEnv dbEnv, DocumentFactory factory) {
        String TRACE_HEADER = "parseProjectionExpression";
        dbEnv.dbAssert(string != null, "parseProjectionExpression", "string should not be null", new Object[0]);

        ExpressionErrorListener errorListener = new ExpressionErrorListener(dbEnv);
        ParseTree tree = null;

        try {
            tree = DynamoDbExpressionParser.parseProjection(string, errorListener);
        } catch (RedundantParenthesesException var9) {
            dbEnv.dbAssert(false, "parseProjectionExpression", "did not expect any parentheses in a ProjectionExpression!!", new Object[]{"projectionExpression", string});
        }

        List<DocPath> pathList = (List) ASTListener.translateTree(tree, string, dbEnv);
        ExpressionValidator validator = new ExpressionValidator(dbEnv, new ParameterMap(attributeNameParameters, factory));
        return new ProjectionExpressionWrapper(pathList, validator);
    }

    public static UpdateExpressionWrapper parseUpdateExpression(String string, Map<String, String> attributeNameParameters, Map<String, DocumentNode> literalParameters, DbEnv dbEnv, DocumentFactory factory) {
        String TRACE_HEADER = "parseUpdateExpression";
        dbEnv.dbAssert(string != null, "parseUpdateExpression", "string should not be null", new Object[0]);

        ExpressionErrorListener errorListener = new ExpressionErrorListener(dbEnv);
        ParseTree tree = null;

        try {
            tree = DynamoDbExpressionParser.parseUpdate(string, errorListener);
        } catch (RedundantParenthesesException var11) {
            dbEnv.throwValidationError(DbValidationError.REDUNDANT_PARENTHESES, new Object[0]);
        }

        List<UpdateListNode> updateList = (List) ASTListener.translateTree(tree, string, dbEnv);
        ParameterMap parameters = new ParameterMap(attributeNameParameters, literalParameters, factory);
        ExpressionValidator validator = new ExpressionValidator(dbEnv, parameters);
        return new UpdateExpressionWrapper(updateList, validator);
    }

    public static ExpressionWrapper parseExpression(String string, Map<String, String> attributeNameParameters, Map<String, DocumentNode> literalParameters, DbEnv dbEnv, DocumentFactory factory) {
        String TRACE_HEADER = "parseExpression";
        dbEnv.dbAssert(string != null, "parseExpression", "string should not be null", new Object[0]);

        ExpressionErrorListener errorListener = new ExpressionErrorListener(dbEnv);
        ParseTree tree = null;

        try {
            tree = DynamoDbExpressionParser.parseCondition(string, errorListener);
        } catch (RedundantParenthesesException var11) {
            dbEnv.throwValidationError(DbValidationError.REDUNDANT_PARENTHESES, new Object[0]);
        }

        ExprTreeNode conditions = (ExprTreeNode) ASTListener.translateTree(tree, string, dbEnv);
        ParameterMap parameters = new ParameterMap(attributeNameParameters, literalParameters, factory);
        ExpressionValidator validator = new ExpressionValidator(dbEnv, parameters);
        return new ExpressionWrapper(conditions, validator);
    }

    public static void validateAttributeNameParameter(Map<String, String> attributeNameParameters, DbEnv dbEnv) {
        if (attributeNameParameters != null) {
            Iterator var2 = attributeNameParameters.entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry<String, String> attributeNameParameter = (Map.Entry) var2.next();
                String key = (String) attributeNameParameter.getKey();
                if (key == null) {
                    dbEnv.throwValidationError(DbValidationError.PARAMETER_MAP_NULL_KEY, new Object[0]);
                }

                if (key.length() == 0) {
                    dbEnv.throwValidationError(DbValidationError.PARAMETER_MAP_EMPTY_KEY, new Object[0]);
                }

                if (key.length() > dbEnv.getConfigInt(DbConfig.MAX_PARAMETER_KEY_SIZE)) {
                    dbEnv.throwValidationError(DbValidationError.PARAMETER_MAP_KEY_SIZE_EXCEEDED, new Object[]{"size of key", key.length()});
                }

                ANTLRErrorListener errorListener = new ParameterMapErrorListener(dbEnv, key);
                DynamoDbExpressionParser.parseAttributeNamesMapKeys(key, errorListener);
            }
        }

    }

    public static void validateLiteralParametersKeys(Set<String> keys, DbEnv dbEnv) {
        if (keys != null) {
            Iterator var2 = keys.iterator();

            while (var2.hasNext()) {
                String key = (String) var2.next();
                if (key == null) {
                    dbEnv.throwValidationError(DbValidationError.PARAMETER_MAP_NULL_KEY, new Object[0]);
                }

                if (key.length() == 0) {
                    dbEnv.throwValidationError(DbValidationError.PARAMETER_MAP_EMPTY_KEY, new Object[0]);
                }

                if (key.length() > dbEnv.getConfigInt(DbConfig.MAX_PARAMETER_KEY_SIZE)) {
                    dbEnv.throwValidationError(DbValidationError.PARAMETER_MAP_KEY_SIZE_EXCEEDED, new Object[0]);
                }

                ANTLRErrorListener errorListener = new ParameterMapErrorListener(dbEnv, key);
                DynamoDbExpressionParser.parseAttributeValuesMapKeys(key, errorListener);
            }
        }
    }
}
