package com.datadynamics.bigdata.api.service.dynamo.logging;

import com.datadynamics.bigdata.api.service.iam.model.DataSourceTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "api_dynamo_usage_logging")
public class DynamoUsageLogging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    String username;

    /**
     * 이 테이블이 Oracle인지 Greenplum에 있는 테이블인지를 식별하기 위한 코드.
     */
    @Column(name = "data_source_type", columnDefinition = "VARCHAR(20)", nullable = true)
    @Enumerated(EnumType.STRING)
    DataSourceTypeEnum dataSourceType;

    /**
     * 스키마명
     */
    @Column(name = "schema_name")
    String schemaName;

    /**
     * 테이블명
     */
    @Column(name = "table_name")
    String tableName;

    /**
     * 이 테이블이 Oracle인지 Greenplum에 있는 테이블인지를 식별하기 위한 코드.
     */
    @Column(name = "access_type", columnDefinition = "VARCHAR(20)", nullable = false)
    @Enumerated(EnumType.STRING)
    DynamoAccessTypeEnum accessType;

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
