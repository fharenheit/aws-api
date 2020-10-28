package com.datadynamics.bigdata.api.config;

import com.datadynamics.bigdata.api.shared.RequestAndResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 개발을 위해서 HTTP에 대한 필터를 적용한다.
     * 운영시 사용하지 않는다.
     */
//    @Bean
    public FilterRegistrationBean<RequestAndResponseLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestAndResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestAndResponseLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}