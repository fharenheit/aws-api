package com.datadynamics.bigdata.api.service.dynamo.commands;

import com.datadynamics.bigdata.api.service.s3.commands.S3RequestCommand;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CreateBucketRequestCommand implements S3RequestCommand {

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
        return null;
    }
}
