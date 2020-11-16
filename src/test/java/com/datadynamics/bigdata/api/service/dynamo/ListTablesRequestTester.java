package com.datadynamics.bigdata.api.service.dynamo;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

import java.util.List;

public class ListTablesRequestTester {

    public static void main(String[] args) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

        AwsClientBuilder.EndpointConfiguration configuration
                = new AwsClientBuilder.EndpointConfiguration("http://localhost:8080/dynamo", "korea");

        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
        builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
        builder.setEndpointConfiguration(configuration);

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxErrorRetry(0); // 0로 하지 않으면 여러번 호출한다.
        builder.setClientConfiguration(clientConfiguration);

        AmazonDynamoDB client = builder.build();

        ListTablesRequest request;
        request = new ListTablesRequest()
                .withLimit(10)
                .withExclusiveStartTableName("last_name");

        ListTablesResult listTablesResult = client.listTables(request);
        List<String> tableNames = listTablesResult.getTableNames();

        for (String tableName : tableNames) {
            System.out.format("* %s\n", tableName);
        }
    }
}
