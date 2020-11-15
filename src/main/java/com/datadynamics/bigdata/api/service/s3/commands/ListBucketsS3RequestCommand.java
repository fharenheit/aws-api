package com.datadynamics.bigdata.api.service.s3.commands;

import com.datadynamics.bigdata.api.service.s3.model.Bucket;
import com.datadynamics.bigdata.api.service.s3.model.http.CanonicalUser;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsEntry;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsList;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsResult;
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

public class ListBucketsS3RequestCommand extends DefaultS3RequestCommand implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private BucketRepository repository;

    @Override
    public String getHttpMethod() {
        return "GET";
    }

    @Override
    public String[] getUri() {
        return new String[]{"/", ""};
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/xml");
        response.setHeader("x-amzn-RequestId", request.getHeader("amz-sdk-invocation-id"));

        // 외부에 노출이 가능하고, 공용 버킷을 모두 조회한다.
        // 요건에 따라서 조회 조건이 변경될 수 있다.
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

    ListAllMyBucketsResult createListAllMyBucketsResult(\) {
        ListAllMyBucketsResult result = new ListAllMyBucketsResult();
        ListAllMyBucketsList buckets = new ListAllMyBucketsList();
        ListAllMyBucketsEntry bucket = new ListAllMyBucketsEntry();
        bucket.setName();
        bucket.setCreationDate();
        buckets.getBucket().add(bucket);
        result.setBuckets(buckets);

        CanonicalUser owner = new CanonicalUser();
        owner.setDisplayName("");
        owner.setID("");
        result.setOwner(owner);
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        repository = this.applicationContext.getBean(BucketRepository.class);
    }
}
