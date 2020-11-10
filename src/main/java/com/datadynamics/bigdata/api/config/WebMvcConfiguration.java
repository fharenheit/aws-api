package com.datadynamics.bigdata.api.config;

import com.amazonaws.HttpMethod;
import com.datadynamics.bigdata.api.shared.RequestAndResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**").addResourceLocations("/WEB-INF/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration registration = registry.addMapping("/**");
        registration.allowedMethods(HttpMethod.GET.name(), HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name());
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