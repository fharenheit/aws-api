package com.datadynamics.bigdata.api.service.iam;

import com.datadynamics.bigdata.api.service.iam.commands.IamRequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/iam")
public class IamFrontController {

    @Autowired
    IamRequestDispatcher requestDispatcher;

    @RequestMapping(method = {GET, POST, PUT, DELETE, PATCH})
    public ResponseEntity service(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestBody String body) {
        IamRequestCommand command = requestDispatcher.getCommand(request, body);
        try {
            return command.execute(request, response, body);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
