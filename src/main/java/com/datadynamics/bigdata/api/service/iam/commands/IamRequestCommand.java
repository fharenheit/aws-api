package com.datadynamics.bigdata.api.service.iam.commands;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IamRequestCommand {

    String getName();

    ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) throws Exception;

}
