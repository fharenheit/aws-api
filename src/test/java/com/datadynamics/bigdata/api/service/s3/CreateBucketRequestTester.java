package com.datadynamics.bigdata.api.service.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;

public class CreateBucketRequestTester {

    public static void main(String[] args) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

        AwsClientBuilder.EndpointConfiguration configuration
                = new AwsClientBuilder.EndpointConfiguration("http://server:8080/s3", "korea");

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProxyProtocol(Protocol.HTTP);
        clientConfiguration.setProxyHost("server");
        clientConfiguration.setProxyPort(80);
        clientConfiguration.setMaxErrorRetry(0); // 0로 하지 않으면 여러번 호출한다.

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
        builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
        builder.setEndpointConfiguration(configuration);
        builder.setClientConfiguration(clientConfiguration);
        AmazonS3 s3 = builder.build();

        CreateBucketRequest request = new CreateBucketRequest("helloworld", "korea");
        Bucket bucket = s3.createBucket(request);

        System.out.println(bucket);
        System.out.println(bucket.getOwner());
        System.out.println(bucket.getName());
        System.out.println(bucket.getCreationDate());
    }

}
