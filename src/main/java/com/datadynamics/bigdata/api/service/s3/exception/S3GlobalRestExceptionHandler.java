package com.datadynamics.bigdata.api.service.s3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(basePackages = "com.datadynamics.bigdata.api.service.s3")
@RestController
public class S3GlobalRestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UnsupportedOperationException.class)
    public String handleBaseException(UnsupportedOperationException e) {
        return e.getMessage();
    }

}
