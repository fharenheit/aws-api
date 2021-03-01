package com.datadynamics.bigdata.api.service.s3;

import com.amazonaws.DefaultRequest;
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

    response = client.put_object(
        ACL='private',
        Body=b'bytes',
        Bucket='string',
        CacheControl='string',
        ContentDisposition='string',
        ContentEncoding='string',
        ContentLanguage='string',
        ContentLength=5,
        ContentMD5='string',
        Key='string',
        ContentType='string'
    )

    print(response)
 */
public class PutObjectVerification {

    public static void main(String[] args) throws URISyntaxException, IOException {
        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName("korea");
        signer.setServiceName("s3");
        signer.setEndpointPrefix("http://localhost:8080/s3");

        MockHttpServletRequest servletRequest = new MockHttpServletRequest("PUT", "/s3/string/string");
        servletRequest.setContent("".getBytes());
        servletRequest.addHeader("Host", "localhost:8080");
        servletRequest.addHeader("X-Amz-Date", "20210301T102646Z");
        servletRequest.addHeader("x-amz-content-sha256", "required");

        servletRequest.addHeader("cache-control", "string");
        servletRequest.addHeader("content-disposition", "string");
        servletRequest.addHeader("content-encoding", "string");
        servletRequest.addHeader("content-language", "string");
        servletRequest.addHeader("content-md5", "string");
        servletRequest.addHeader("content-type", "string");
        servletRequest.addHeader("x-amz-acl", "private");
        servletRequest.addHeader("content-length", "5");
        servletRequest.addHeader("Authorization", "AWS4-HMAC-SHA256 Credential=admin/20210301/korea/s3/aws4_request, SignedHeaders=cache-control;content-disposition;content-encoding;content-language;content-length;content-md5;content-type;host;x-amz-acl;x-amz-content-sha256;x-amz-date, Signature=92bb769e6199e4f87f9d164952b4ef08a0e8af6e85340563396e7ec2641ebfc4");
        servletRequest.setContent("bytes".getBytes());

        signer.setBoto3(true);
        String username = signer.getUsername(servletRequest);
        String password = "admin123";

        DefaultRequest request = signer.sign(servletRequest, username, password);
        String authorization = (String) request.getHeaders().get("Authorization");
        System.out.println(authorization);
    }
}