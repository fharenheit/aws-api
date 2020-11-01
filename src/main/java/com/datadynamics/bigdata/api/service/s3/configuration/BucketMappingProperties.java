package com.datadynamics.bigdata.api.service.s3.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <pre>application.yml</pre> 파일의 <pre>app.s3.bucket-mapping</pre>에 정의되어 있는 HDFS Path와 S3 Bucket 매핑 정보를 로딩하여
 * 속성으로 관리하는 Properties. 이 매핑 정보를 사용하려면 <pre>@Autowire</pre>로 사용하도록 한다.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.s3.bucket-mapping")
public class BucketMappingProperties {

    private Map<String, String> bucketMapping;

}
