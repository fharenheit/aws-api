package com.datadynamics.bigdata.api.service.iam;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysRequest;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysResult;

public class ListAccessKeysRequestTester {

    public static void main(String[] args) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

        AwsClientBuilder.EndpointConfiguration configuration = new AwsClientBuilder.EndpointConfiguration("http://localhost:8080/iam", "korea");

        AmazonIdentityManagementClientBuilder builder = AmazonIdentityManagementClientBuilder.standard();
        builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
        builder.setEndpointConfiguration(configuration);
        final AmazonIdentityManagement iam = builder.build();

        ListAccessKeysRequest request1 = new ListAccessKeysRequest();
        ListAccessKeysResult result1 = iam.listAccessKeys(request1);
        System.out.println(result1.toString());

        ListAccessKeysRequest request2 = new ListAccessKeysRequest().withUserName("fharenheit");
        ListAccessKeysResult result2 = iam.listAccessKeys(request2);
        System.out.println(result2.toString());
    }

}
