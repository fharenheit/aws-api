package com.datadynamics.bigdata.api.service.iam;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CreateUserRequestTester {

    public static void main(String[] args) throws Exception {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

        AwsClientBuilder.EndpointConfiguration configuration = new AwsClientBuilder.EndpointConfiguration("http://localhost:8080/iam", "korea");

        AmazonIdentityManagementClientBuilder builder = AmazonIdentityManagementClientBuilder.standard();
        builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
        builder.setEndpointConfiguration(configuration);

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxErrorRetry(0); // 0로 하지 않으면 여러번 호출한다.
        builder.setClientConfiguration(clientConfiguration);

        final AmazonIdentityManagement iam = builder.build();

        CreateUserRequest request = new CreateUserRequest("fharenheit5");
        request.setPath("/");
        request.setPermissionsBoundary("blahblahblahblahblah"); // 실체 파악 필요
        request.setTags(toTags("user", "test"));

        CreateUserResult response = iam.createUser(request);

        System.out.println(response.getUser());
    }

    public static Collection<Tag> toTags(Object... objects) {
        List list = new ArrayList();
        Tag tag1 = new Tag();
        tag1.setKey("name");
        tag1.setValue("hong");
        list.add(tag1);

        Tag tag2 = new Tag();
        tag2.setKey("depart");
        tag2.setValue("C101");
        list.add(tag2);

        return list;
    }

}
