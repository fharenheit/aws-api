package com.datadynamics.bigdata.api.service.s3;

import com.amazonaws.DefaultRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.s3.model.ListBucketsRequest;
import com.datadynamics.bigdata.api.auth.AWS4Signer;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class SignatureVerifier {

    public static void main(String[] args) throws URISyntaxException {
        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName("korea");
        signer.setServiceName("s3");
        signer.setEndpointPrefix("http://localhost:8080/s3");

        ListBucketsRequest req = new ListBucketsRequest();
        req.getRequestClientOptions().setReadLimit(13000);

        DefaultRequest request = new DefaultRequest(req, "Amazon S3");
        request.setEndpoint(new URI("http://localhost:8080/s3"));
        request.setHttpMethod(HttpMethodName.POST);
        request.getHeaders().put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        request.getHeaders().put("Host", "localhost:8080");
        request.getHeaders().put("X-Amz-Date", "20210214T034805Z");

        request.setContent(new ByteArrayInputStream("Action=ListUsers&Version=2010-05-08".getBytes()));

        signer.sign(request, new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return "admin";
            }

            @Override
            public String getAWSSecretKey() {
                return "admin123";
            }
        }, "");

        Object authorization = request.getHeaders().get("Authorization");
        System.out.println(authorization);
    }
}