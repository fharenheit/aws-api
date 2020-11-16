package com.datadynamics.bigdata.api.service.dynamo;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import java.util.List;

public class DescribeTableRequestTester {

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

        try {
            TableDescription table = client.describeTable("helloworld").getTable();

            if (table != null) {
                System.out.format("Table name  : %s\n", table.getTableName());
                System.out.format("Table ARN   : %s\n", table.getTableArn());
                System.out.format("Status      : %s\n", table.getTableStatus());
                System.out.format("Item count  : %d\n", table.getItemCount().longValue());
                System.out.format("Size (bytes): %d\n", table.getTableSizeBytes().longValue());

                List<AttributeDefinition> attributes = table.getAttributeDefinitions();
                if (attributes != null) {
                    System.out.println("Attributes");
                    for (AttributeDefinition a : attributes) {
                        System.out.format("  %s (%s)\n", a.getAttributeName(), a.getAttributeType());
                    }
                }
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }
}
