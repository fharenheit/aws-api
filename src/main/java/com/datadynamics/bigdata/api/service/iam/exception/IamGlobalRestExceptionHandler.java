package com.datadynamics.bigdata.api.service.iam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(basePackages = "com.datadynamics.bigdata.api.service.iam")
@RestController
public class IamGlobalRestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UnsupportedOperationException.class)
    public String handleBaseException(UnsupportedOperationException e) {
        return e.getMessage();
    }

}
