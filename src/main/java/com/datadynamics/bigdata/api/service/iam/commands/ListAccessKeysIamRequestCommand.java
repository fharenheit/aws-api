package com.datadynamics.bigdata.api.service.iam.commands;

import com.amazonaws.services.identitymanagement.model.StatusType;
import com.datadynamics.bigdata.api.service.iam.model.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ListAccessKeysIamRequestCommand extends IamDefaultRequestCommand implements IamRequestCommand {

    @Override
    public String getName() {
        return "ListAccessKeys";
    }

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response, String body) {
        Map<String, String> localCommand = parseRequestBody(body);
        String userName = localCommand.get("UserName");
        if (StringUtils.isEmpty(userName)) {
            // 모든 사용자의 Access Key를 반환
        } else {
            // 특정 사용자의 Access Key만 반환
        }

        List<Member> members = new ArrayList();
        members.add(Member.builder()
                .status(StatusType.Active.toString())
                .accessKeyId(UUID.randomUUID().toString())
                .userName("fharenheit")
                .createDate(new Date())
                .build());

        ListAccessKeysResult listAccessKeysResult = ListAccessKeysResult.builder()
                .accessKeyMetadata(AccessKeyMetadata.builder().members(members).build())
                .userName("fharenheit")
                .isTruncated(false)
                .build();

        ListAccessKeysResponse res = ListAccessKeysResponse.builder()
                .listAccessKeysResult(listAccessKeysResult)
                .responseMetadata(ResponseMetadata.builder().requestId(UUID.randomUUID().toString()).build())
                .build();
        return ResponseEntity.ok(res);
    }

}
