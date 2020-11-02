package com.datadynamics.bigdata.api.service.s3.commands;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CreateBucketS3RequestCommand implements S3RequestCommand {

    @Override
    public String getHttpMethod() {
        return "POST";
    }

    @Override
    public String getUri() {
        return "/";
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.badRequest().body("지원하지 않는 기능입니다.");
    }
}
