package com.datadynamics.bigdata.api.service.s3.commands;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class DefaultS3RequestCommand implements S3RequestCommand {

    public String getUsername(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return StringUtils.substringBetween(authorization, "Credential=", "/");
    }

}
