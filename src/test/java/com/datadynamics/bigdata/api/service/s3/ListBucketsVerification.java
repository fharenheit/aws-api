package com.datadynamics.bigdata.api.service.s3;

import com.amazonaws.DefaultRequest;
import com.amazonaws.services.s3.model.ListBucketsRequest;
import com.datadynamics.bigdata.api.auth.AWS4Signer;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.IOException;
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

    response = client.list_buckets(
    )

    print(response)
 */
public class ListBucketsVerification {

    public static void main(String[] args) throws URISyntaxException, IOException {
        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName("korea");
        signer.setServiceName("s3");
        signer.setEndpointPrefix("http://localhost:8080/s3");

        ListBucketsRequest req = new ListBucketsRequest();
        req.getRequestClientOptions().setReadLimit(13000);

        MockHttpServletRequest servletRequest = new MockHttpServletRequest("GET", "/s3");
        servletRequest.setContent("".getBytes());
        servletRequest.addHeader("Host", "localhost:8080");
        servletRequest.addHeader("X-Amz-Date", "20210301T093639Z");
        servletRequest.addHeader("x-amz-content-sha256", "required");
        servletRequest.addHeader("Authorization", " AWS4-HMAC-SHA256 Credential=admin/20210301/korea/s3/aws4_request, SignedHeaders=host;x-amz-content-sha256;x-amz-date, Signature=e668fb96c44dab9304846612983c742aaccb0a174d70bd4b22d40a857408f2b3");
        servletRequest.setContent("".getBytes());

        signer.setBoto3(true);
        String username = signer.getUsername(servletRequest);
        String password = "admin123";

        DefaultRequest request = signer.sign(servletRequest, username, password);
        String authorization = (String) request.getHeaders().get("Authorization");
        System.out.println(authorization);
    }
}