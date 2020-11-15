package com.datadynamics.bigdata.api.service.s3.commands;

import com.datadynamics.bigdata.api.service.s3.model.Bucket;
import com.datadynamics.bigdata.api.service.s3.model.ModelUtils;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsResult;
import com.datadynamics.bigdata.api.service.s3.repository.BucketRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        String username = getUsername(request);

        // 외부에 노출이 가능하고, 공용 버킷을 모두 조회한다. 요건에 따라서 조회 조건이 변경될 수 있다.
        List<Bucket> byBuckets = repository.findBucketsBySharedAndExposed();
        if (byBuckets == null || byBuckets.size() < 1) {
            return ResponseEntity.ok(new ListAllMyBucketsResult());
        } else {
            List<String> bucketNames = new ArrayList<>();
            for (Bucket b : byBuckets) {
                bucketNames.add(b.getBucketName());
            }

            ListAllMyBucketsResult listAllMyBucketsResult = ModelUtils.listBuckets(username, username, bucketNames.toArray(new String[byBuckets.size()]));
            return ResponseEntity.ok(listAllMyBucketsResult);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        repository = this.applicationContext.getBean(BucketRepository.class);
    }
}
