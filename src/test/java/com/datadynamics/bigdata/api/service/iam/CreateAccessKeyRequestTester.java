package com.datadynamics.bigdata.api.service.iam;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyResult;

public class CreateAccessKeyRequestTester {

    public static void main(String[] args) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

        AwsClientBuilder.EndpointConfiguration configuration = new AwsClientBuilder.EndpointConfiguration("http://localhost:8080/iam", "korea");

        AmazonIdentityManagementClientBuilder builder = AmazonIdentityManagementClientBuilder.standard();
        builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
        builder.setEndpointConfiguration(configuration);
        final AmazonIdentityManagement iam = builder.build();

        CreateAccessKeyRequest request = new CreateAccessKeyRequest().withUserName("fharenheit");
        CreateAccessKeyResult response = iam.createAccessKey(request);
    }

}
