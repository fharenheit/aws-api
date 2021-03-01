package com.datadynamics.bigdata.api.service.s3;

import com.amazonaws.DefaultRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.s3.model.ListBucketsRequest;
import com.datadynamics.bigdata.api.auth.AWS4Signer;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/*
    import boto3
    import hashlib

    session = boto3.session.Session()

    client = session.client(
        service_name='s3',
        region_name='korea',
        aws_access_key_id='admin',
        aws_secret_access_key='admin123',
        endpoint_url='http://localhost:8080/s3',
    )

    response = client.create_bucket(
        ACL='private',
        Bucket='string',
        GrantFullControl='string',
        GrantRead='string',
        GrantReadACP='string',
        GrantWrite='string',
        GrantWriteACP='string',
        ObjectLockEnabledForBucket=True
    )

    print(response)
 */
public class CreateBucketVerification {

    public static void main(String[] args) throws URISyntaxException, IOException {
        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName("korea");
        signer.setServiceName("s3");
        signer.setEndpointPrefix("http://localhost:8080/s3");

        MockHttpServletRequest servletRequest = new MockHttpServletRequest("PUT", "/s3/string");
        servletRequest.setContent("".getBytes());
        servletRequest.addHeader("Host", "localhost:8080");
        servletRequest.addHeader("X-Amz-Date", "20210301T095538Z");
        servletRequest.addHeader("x-amz-content-sha256", "required");
        servletRequest.addHeader("x-amz-acl", "private");
        servletRequest.addHeader("x-amz-grant-full-control", "string");
        servletRequest.addHeader("x-amz-grant-read-acp", "string");
        servletRequest.addHeader("x-amz-grant-read", "string");
        servletRequest.addHeader("x-amz-grant-write", "string");
        servletRequest.addHeader("x-amz-grant-write-acp", "string");
        servletRequest.addHeader("x-amz-bucket-object-lock-enabled", "True");
        servletRequest.addHeader("Authorization", "AWS4-HMAC-SHA256 Credential=admin/20210301/korea/s3/aws4_request, SignedHeaders=host;x-amz-acl;x-amz-bucket-object-lock-enabled;x-amz-content-sha256;x-amz-date;x-amz-grant-full-control;x-amz-grant-read;x-amz-grant-read-acp;x-amz-grant-write;x-amz-grant-write-acp, Signature=0324d5810d4c320c3d07c86346fd5599acc6307e69ab53863f49843883e78c29");
        servletRequest.setContent("".getBytes());

        signer.setBoto3(true);
        String username = signer.getUsername(servletRequest);
        String password = "admin123";

        DefaultRequest request = signer.sign(servletRequest, username, password);
        String authorization = (String) request.getHeaders().get("Authorization");
        System.out.println(authorization);
    }
}