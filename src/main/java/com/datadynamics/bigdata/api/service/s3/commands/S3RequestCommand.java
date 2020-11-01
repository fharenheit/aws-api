package com.datadynamics.bigdata.api.service.s3.commands;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface S3RequestCommand {

    String getName();

    ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response, String body);

}
