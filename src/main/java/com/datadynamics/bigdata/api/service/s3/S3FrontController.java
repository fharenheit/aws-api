package com.datadynamics.bigdata.api.service.s3;

import com.datadynamics.bigdata.api.service.s3.commands.S3RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/${app.service-context-paths.s3}")
public class S3FrontController {

    @Autowired
    S3RequestDispatcher requestDispatcher;

    /**
     * 모든 요청을 받아서 일단 Request Dispatcher로 요청을 전달한다.
     * 각 서비스마다 Request 구성이 다르므로 Request를 해석하여 실행하는 방법도 다르므로
     * 각 서비스별로 Request Dispatcher를 구성한다.
     */
    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH})
    public ResponseEntity service(@RequestHeader Map<String, String> headers, HttpServletRequest request, HttpServletResponse response) {
        S3RequestCommand command = requestDispatcher.getCommand(headers, request);
        return command.execute(headers, request, response);
    }

}
