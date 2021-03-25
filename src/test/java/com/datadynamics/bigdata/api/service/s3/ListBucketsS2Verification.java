package com.datadynamics.bigdata.api.service.s3;

import com.amazonaws.DefaultRequest;
import com.amazonaws.services.s3.model.ListBucketsRequest;
import com.datadynamics.bigdata.api.auth.AWS2Signer;
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
public class ListBucketsS2Verification {

    public static void main(String[] args) throws URISyntaxException, IOException {
    }
}