package com.datadynamics.bigdata.api.service.iam;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateGroupRequest;
import com.amazonaws.services.identitymanagement.model.CreateGroupResult;

public class CreateGroupRequestTester {

    public static void main(String[] args) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

        AwsClientBuilder.EndpointConfiguration configuration = new AwsClientBuilder.EndpointConfiguration("http://localhost:8080/iam", "korea");

        AmazonIdentityManagementClientBuilder builder = AmazonIdentityManagementClientBuilder.standard();
        builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
        builder.setEndpointConfiguration(configuration);

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxErrorRetry(0); // 0로 하지 않으면 여러번 호출한다.
        builder.setClientConfiguration(clientConfiguration);

        final AmazonIdentityManagement iam = builder.build();

        CreateGroupRequest request = new CreateGroupRequest("admin");
        request.setPath("/");
        CreateGroupResult response = iam.createGroup(request);
        System.out.println(response);
    }

}
