package com.datadynamics.bigdata.api.service.iam.commands;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class IamDefaultRequestCommand {

    public Map<String, String> parseRequestBody(String body) {
        Map<String, String> map = new HashMap<>();
        String[] entries = body.split("&");
        for (String entry : entries) {
            String[] split = StringUtils.split(entry, "=");
            map.put(split[0], split[1]);
        }
        return map;
    }

}
