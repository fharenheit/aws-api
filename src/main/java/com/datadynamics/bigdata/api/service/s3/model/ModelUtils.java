package com.datadynamics.bigdata.api.service.s3.model;

import com.datadynamics.bigdata.api.service.s3.model.http.CanonicalUser;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsEntry;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsList;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsResult;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class ModelUtils {

    public static ListAllMyBucketsResult listBuckets(String id, String owner, String... buckets) {
        ListAllMyBucketsResult result = new ListAllMyBucketsResult();
        result.setBuckets(buckets(buckets));
        result.setOwner(owner(id, owner));
        return result;
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
