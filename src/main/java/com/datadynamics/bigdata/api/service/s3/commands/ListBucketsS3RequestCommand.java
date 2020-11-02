package com.datadynamics.bigdata.api.service.s3.commands;

import com.datadynamics.bigdata.api.service.s3.model.Bucket;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsResult;
import com.datadynamics.bigdata.api.service.s3.model.http.Owner;
import com.datadynamics.bigdata.api.service.s3.repository.BucketRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListBucketsS3RequestCommand implements S3RequestCommand, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private BucketRepository repository;

    @Override
    public String getHttpMethod() {
        return "GET";
    }

    @Override
    public String getUri() {
        return "/";
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/xml");
        response.setHeader("x-amzn-RequestId", request.getHeader("amz-sdk-invocation-id"));

        List<Bucket> byBuckets = repository.findBucketsBySharedAndExposed();
        if (byBuckets == null || byBuckets.size() < 1) {
            return ResponseEntity.ok(new ListAllMyBucketsResult());
        } else {
            List<com.datadynamics.bigdata.api.service.s3.model.http.Bucket> buckets = new ArrayList();
            byBuckets.stream().forEach(bucket -> {
                buckets.add(createBucket(bucket.getBucketName(), bucket.getCreateTime()));
            });

            ListAllMyBucketsResult result = createListAllMyBucketsResult(buckets, createOwner("실명", "SSO ID"));
            return ResponseEntity.ok(result);
        }
    }

    com.datadynamics.bigdata.api.service.s3.model.http.Bucket createBucket(String name, Timestamp createTime) {
        com.datadynamics.bigdata.api.service.s3.model.http.Bucket bucket = new com.datadynamics.bigdata.api.service.s3.model.http.Bucket();
        bucket.setName(name);
        bucket.setCreationDate(createTime);
        return bucket;
    }

    ListAllMyBucketsResult createListAllMyBucketsResult(List<com.datadynamics.bigdata.api.service.s3.model.http.Bucket> buckets, Owner owner) {
        ListAllMyBucketsResult result = new ListAllMyBucketsResult();
        result.setBuckets(buckets);
        result.setOwner(owner);
        return result;
    }

    Owner createOwner(String displayName, String id) {
        Owner owner = new Owner();
        owner.setDisplayName(displayName);
        owner.setId(id);
        return owner;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        repository = this.applicationContext.getBean(BucketRepository.class);
    }
}
