package com.datadynamics.bigdata.api.service.iam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "api_iam_service")
public class Service {

    /**
     * 서비스를 식별하기 위한 서비스 코드 (예; DYNAMODB)
     */
    @Id
    @Column(name = "service_code", columnDefinition = "VARCHAR(100)")
    String serviceCode;

    /**
     * 서비스 명칭 (예; DynamoDB)
     */
    @Column(name = "service_name", columnDefinition = "VARCHAR(10)", nullable = false)
    String serviceName;

    /**
     * 서비스의 세부 설명
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)", nullable = true)
    String description;

    /**
     * 서비스의 Endpoint 주소(예; http://192.168.1.1:8080/s3)
     */
    @Column(name = "endpoint", columnDefinition = "VARCHAR(255)", nullable = true)
    String endpoint;

    /**
     * 생성일 (이 필드에는 값을 입력하지 않아도 Hibernate가 INSERT시 자동으로 기록)
     */
    @CreationTimestamp
    @Column(name = "create_ts", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    /**
     * 변경일 (이 필드에는 값을 입력하지 않아도 Hibernate가 UPDATE시 자동으로 기록)
     */
    @UpdateTimestamp
    @Column(name = "update_ts", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
