package com.datadynamics.bigdata.api.service.iam.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * UserName으로 지정한 사용자의 새로운 Access Key를 생성한다.
 * 새로운 Access Key를 생성하면 기존의 Access Key를 제거되어야 하며
 * Secret Key 또한 자동으로 생성되어야 한다. 그리고 Access Key와 Secret Key는 DB에 저장되어야 한다.
 */
@Slf4j
public class CreateAccessKeyIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand {

    @Override
    public String getName() {
        return "CreateAccessKey";
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response, String body) {
        Map<String, String> localCommand = parseRequestBody(body);
        String userName = localCommand.get("UserName");
        log.info("{}", userName);
        log.info("{}", body);
        return null;
    }

}
