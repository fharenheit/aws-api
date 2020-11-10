package com.datadynamics.bigdata.api.service.s3.commands;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class CreateBucketS3RequestCommand extends DefaultS3RequestCommand implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public String getHttpMethod() {
        return "PUT";
    }

    @Override
    public String[] getUri() {
        return new String[]{"", "/"};
    }

    @Override
    public ResponseEntity execute(Map<String, String> headers, HttpServletRequest request, HttpServletResponse response) {
        String username = getUsername(request);
        String bucketName = getBucketName(request);

        // TODO : 여기에서 버킷을 생성한다.

        return ResponseEntity.badRequest().body("지원하지 않는 기능입니다.");
    }

    /**
     * 생성할 버킷명이 HTTP Header의 "host" 값에서 subdomain으로 식별이 되므로 subdomain을 추출하거나
     * 실제 서비스 도메인을 제거하는 방식으로 구현할 수 있다.
     *
     * @param request HTTP Request
     * @return 생성할 버킷명
     */
    private String getBucketName(HttpServletRequest request) {
        String hostname = request.getHeader("host");
        Environment env = applicationContext.getBean(Environment.class);
        String serverName = env.getProperty("app.s3.server-name");
        return StringUtils.replace(hostname, "." + serverName, "");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
