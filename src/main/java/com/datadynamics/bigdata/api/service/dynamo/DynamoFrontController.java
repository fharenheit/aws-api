package com.datadynamics.bigdata.api.service.dynamo;

import com.datadynamics.bigdata.api.service.dynamo.commands.DynamoRequestCommand;
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
public class DynamoFrontController {

    @Autowired
    DynamoRequestDispatcher requestDispatcher;

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH},   produces = "application/x-amz-json-1.0")
    public ResponseEntity service(@RequestHeader Map<String, String> headers,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestBody String body) {
        response.setHeader("Content-Type", "application/x-amz-json-1.0");
        DynamoRequestCommand command = requestDispatcher.getCommand(headers, request, body);
        return command.execute(headers, request, response, body);
    }

}
