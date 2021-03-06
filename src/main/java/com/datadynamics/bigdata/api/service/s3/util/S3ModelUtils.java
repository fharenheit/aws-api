package com.datadynamics.bigdata.api.service.s3.util;

import com.datadynamics.bigdata.api.service.s3.model.http.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class S3ModelUtils {

    public static ListAllMyBucketsResponse listBuckets(String id, String owner, String... buckets) {
        ListAllMyBucketsResult result = new ListAllMyBucketsResult();
        result.setBuckets(buckets(buckets));
        result.setOwner(owner(id, owner));

        ListAllMyBucketsResponse response = new ListAllMyBucketsResponse();
        response.setListAllMyBucketsResponse(result);
        return response;
    }

    public static ListAllMyBucketsList buckets(String... buckets) {
        ListAllMyBucketsList list = new ListAllMyBucketsList();
        Arrays.stream(buckets).forEach(bucketName -> {
            ListAllMyBucketsEntry e = new ListAllMyBucketsEntry();
            e.setName(bucketName);
            e.setCreationDate(creationDate());
            list.getBucket().add(e);
        });
        return list;
    }

    public static XMLGregorianCalendar creationDate() {
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(new Date());
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            // 이런 일이 발생할 가능성은 없음
            return null;
        }
    }

    public static CanonicalUser owner(String id, String name) {
        // Owner ID의 의미를 알 수 없으므로 일단 Owner ID를 username 또는 SSO ID를 넣도록 한다.
        CanonicalUser user = new CanonicalUser();
        user.setDisplayName(name);
        user.setID(id);
        return user;
    }
}
