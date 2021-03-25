package com.datadynamics.bigdata.api.auth;

import com.amazonaws.DefaultRequest;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.http.HttpMethodName;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class AWS2SignerTest {

    @Test
    public void createSigner() throws URISyntaxException {
        String serverName = "server";

        MockHttpServletRequest servletRequest = new MockHttpServletRequest("GET", "/s3");
        servletRequest.setContent("".getBytes());
        servletRequest.addHeader("Host", "server:8080");
        servletRequest.addHeader("Authorization", "AWS baekdae.dl:+LOMoPJwVd/+b+d0z+6t/FhOunM=");
        servletRequest.setContent("".getBytes());

        String authorization = "AWS baekdae.dl:+LOMoPJwVd/+b+d0z+6t/FhOunM=";
        String username = StringUtils.substringsBetween(authorization, "AWS ", ":")[0];
        String password = "admin123";

        DefaultRequest request = new DefaultRequest("Amazon S3");
        request.setEndpoint(new URI("http://localhost:8080/s3"));
        request.setHttpMethod(HttpMethodName.valueOf(servletRequest.getMethod().toUpperCase()));

        Enumeration<String> headerNames = servletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            request.getHeaders().put(name, servletRequest.getHeader(name));
        }
        Map<String, String[]> params = servletRequest.getParameterMap();
        for (Object key : params.keySet()) {
            String[] value = params.get(key);
            request.getParameters().put(key, Arrays.asList(value));
        }
        request.setResourcePath(servletRequest.getRequestURI());

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(username, password);

        AWS2Signer signer = AWS2Signer.createAWSS2Signer(servletRequest, serverName);
        signer.sign(request, awsCreds, null);

        System.out.println(request.getHeaders().get("Authorization"));
    }

}