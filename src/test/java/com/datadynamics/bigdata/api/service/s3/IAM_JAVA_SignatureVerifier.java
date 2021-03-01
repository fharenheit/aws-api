package com.datadynamics.bigdata.api.service.s3;

import com.amazonaws.DefaultRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.identitymanagement.model.GetPolicyRequest;
import com.datadynamics.bigdata.api.auth.AWS4Signer;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class IAM_JAVA_SignatureVerifier {

    public static void main(String[] args) throws URISyntaxException {
        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName("korea");
        signer.setServiceName("iam");
        signer.setEndpointPrefix("http://localhost:8080/iam");

        GetPolicyRequest req = new GetPolicyRequest();
        req.getRequestClientOptions().setReadLimit(131073); // 이 값을 기본값으로 놔두면 0가 되며 Request Body를 제대로 읽지 못하여 Signature가 틀리게 됨
        req.setPolicyArn("arn:aws:iam:korea:1282865533:policy/TestPolicy2");  // Request에 따라 모두 다름

        DefaultRequest request = new DefaultRequest(req, "AmazonIdentityManagement");
        request.setEndpoint(new URI("http://localhost:8080/iam"));
        request.setHttpMethod(HttpMethodName.POST);
        request.getHeaders().put("amz-sdk-invocation-id", "f968fcd1-24e5-b9e6-5473-bed41b3a2d29"); // SDK에서 생성하는 UUID로 헤더값으로 넘어오므로 그대로 사용
        request.getHeaders().put("amz-sdk-request", "attempt=1;max=1");
        request.getHeaders().put("amz-sdk-retry", "0/0/500");
        request.getHeaders().put("Host", "localhost:8080"); // 호스트명이 변경되면 해쉬코드도 다르게 생성됨. S3의 경우 이 값이 Action에 따라 달라짐
        request.getHeaders().put("User-Agent", "aws-sdk-java/1.11.887 Windows_10/10.0 Java_HotSpot(TM)_64-Bit_Server_VM/25.241-b07 java/1.8.0_241 vendor/Oracle_Corporation");
        request.getHeaders().put("X-Amz-Date", "20210202T233818Z");  // SDK 내부에서는 Timestamp가 변하지만 검증시에는 변하지 않도록 조치해야 함
        request.setContent(new ByteArrayInputStream("Action=GetPolicy&Version=2010-05-08&PolicyArn=arn%3Aaws%3Aiam%3Akorea%3A1282865533%3Apolicy%2FTestPolicy2".getBytes())); // Signature 생성에 이 내용이 반영됨. 각 서비스 마다 다름.

        signer.sign(request, new AWSCredentials() { // 사용자의 Credential은 DB에 있으므로 DB를 뒤져서 사용
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