package com.datadynamics.bigdata.api.service.dynamo.commands;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ScanRequestCommand implements DynamoRequestCommand {
    @Override
    public String getName() {
        return "Scan";
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response, String body) {
        return null;
    }
}
