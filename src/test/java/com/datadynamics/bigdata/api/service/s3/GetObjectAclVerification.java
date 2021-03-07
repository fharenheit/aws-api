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

    response = client.get_object_acl(
        Bucket='string',
        Key='string',
        VersionId='string',
        RequestPayer='requester',
        ExpectedBucketOwner='string'
    )

    print(response)

Calculating signature using v4 auth.
CanonicalRequest:
GET
/s3/string/string
acl=&versionId=string
host:localhost:8080
x-amz-content-sha256:e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855
x-amz-date:20210307T141553Z
x-amz-expected-bucket-owner:string
x-amz-request-payer:requester

host;x-amz-content-sha256;x-amz-date;x-amz-expected-bucket-owner;x-amz-request-payer
e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855
StringToSign:
AWS4-HMAC-SHA256
20210307T141553Z
20210307/korea/s3/aws4_request
7938ad78463761414511bfa2d8ac59a8171d09fe42e9bc51fdeffb2fd4837f4a
[string_to_sign]-----------------
AWS4-HMAC-SHA256
20210307T141553Z
20210307/korea/s3/aws4_request
7938ad78463761414511bfa2d8ac59a8171d09fe42e9bc51fdeffb2fd4837f4a
-----------------
Signature:
744680e8d8834f9545ea3d4f617cb05392cfc6924994912a54ff914681663ffa
x-amz-request-payer: requester
x-amz-expected-bucket-owner: string
User-Agent: Boto3/1.17.4 Python/3.9.1 Windows/10 Botocore/1.20.4
X-Amz-Date: 20210307T141553Z
X-Amz-Content-SHA256: e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855
Authorization: AWS4-HMAC-SHA256 Credential=admin/20210307/korea/s3/aws4_request, SignedHeaders=host;x-amz-content-sha256;x-amz-date;x-amz-expected-bucket-owner;x-amz-request-payer, Signature=744680e8d8834f9545ea3d4f617cb05392cfc6924994912a54ff914681663ffa
 */
public class GetObjectAclVerification {

    public static void main(String[] args) throws URISyntaxException, IOException {
        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName("korea");
        signer.setServiceName("s3");
        signer.setEndpointPrefix("http://localhost:8080/s3");

        MockHttpServletRequest servletRequest = new MockHttpServletRequest("GET", "/s3/string/string");
        servletRequest.setContent("".getBytes());

        servletRequest.addParameter("acl", "");
        servletRequest.addParameter("versionId", "string");

        servletRequest.addHeader("Host", "localhost:8080");
        servletRequest.addHeader("X-Amz-Date", "20210307T141553Z");
        servletRequest.addHeader("x-amz-content-sha256", "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
        servletRequest.addHeader("x-amz-expected-bucket-owner", "string");
        servletRequest.addHeader("x-amz-request-payer", "requester");
        servletRequest.addHeader("Authorization", "Authorization: AWS4-HMAC-SHA256 Credential=admin/20210307/korea/s3/aws4_request, SignedHeaders=host;x-amz-content-sha256;x-amz-date;x-amz-expected-bucket-owner;x-amz-request-payer, Signature=744680e8d8834f9545ea3d4f617cb05392cfc6924994912a54ff914681663ffa");

        signer.setBoto3(true);
        String username = signer.getUsername(servletRequest);
        String password = "admin123";

        DefaultRequest request = signer.sign(servletRequest, username, password);
        String authorization = (String) request.getHeaders().get("Authorization");
        System.out.println(authorization);
    }
}