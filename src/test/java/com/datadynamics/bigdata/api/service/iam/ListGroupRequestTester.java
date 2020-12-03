package com.datadynamics.bigdata.api.service.iam;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.ListGroupsRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupsResult;

public class ListGroupRequestTester {

    public static void main(String[] args) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

        AwsClientBuilder.EndpointConfiguration configuration = new AwsClientBuilder.EndpointConfiguration("http://localhost:8080/iam", "korea");

        AmazonIdentityManagementClientBuilder builder = AmazonIdentityManagementClientBuilder.standard();
        builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
        builder.setEndpointConfiguration(configuration);
        final AmazonIdentityManagement iam = builder.build();

        ListGroupsRequest request = new ListGroupsRequest();
        request.setPathPrefix("/");
        ListGroupsResult listGroupsResult = iam.listGroups(request);
        System.out.println(listGroupsResult);
    }

}
