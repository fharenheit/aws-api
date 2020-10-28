package com.datadynamics.bigdata.api.model;

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

    @Id
    @Column(name = "service_code", columnDefinition = "VARCHAR(100)")
    String serviceCode;

    @Column(name = "service_name", columnDefinition = "VARCHAR(10)", nullable = false)
    String serviceName;

    @Column(name = "description", columnDefinition = "VARCHAR(255)", nullable = true)
    String description;

    @Column(name = "endpoint", columnDefinition = "VARCHAR(255)", nullable = true)
    String endpoint;

    @CreationTimestamp
    @Column(name = "create_ts", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_ts", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
