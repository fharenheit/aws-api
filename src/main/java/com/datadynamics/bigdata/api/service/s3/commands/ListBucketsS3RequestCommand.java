package com.datadynamics.bigdata.api.service.s3.commands;

import com.datadynamics.bigdata.api.service.s3.model.Bucket;
import com.datadynamics.bigdata.api.service.s3.model.http.ListAllMyBucketsResponse;
import com.datadynamics.bigdata.api.service.s3.repository.BucketRepository;
import com.datadynamics.bigdata.api.service.s3.util.S3ModelUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
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

        File file = new File("C:/");
        String[] dirs = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return dir.isDirectory();
            }
        });

        return ResponseEntity.ok(S3ModelUtils.listBuckets(username, username, dirs));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        repository = this.applicationContext.getBean(BucketRepository.class);
    }
}
