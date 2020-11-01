package com.datadynamics.bigdata.api.service.s3;

import com.datadynamics.bigdata.api.service.s3.commands.S3RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/dynamo")
public class S3FrontController {

    @Autowired
    S3RequestDispatcher requestDispatcher;

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH})
    public ResponseEntity service(@RequestHeader Map<String, String> headers,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestBody String body) {
        S3RequestCommand command = requestDispatcher.getCommand(headers, request, body);
        return command.execute(headers, request, response, body);
    }

}
