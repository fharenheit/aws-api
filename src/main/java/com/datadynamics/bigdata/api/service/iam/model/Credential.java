package com.datadynamics.bigdata.api.service.iam.model;

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
@Entity(name = "api_iam_credential")
public class Credential {

    /**
     * 사용자 ID
     */
    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    String username;

    /**
     * Access Key.
     * 사용자가 여러 개의 Access Key를 생성하지 못하도록 해야 하며 실제로 여러 개를 만들어서 사용할 필요가 없음.
     * 따라서 Access Key는 Username과 동일하게 생성할 것을 권장함.
     */
    @Column(name = "access_key", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    String accessKey;

    /**
     * Secret Key.
     * 사용자가 설정을 할 수 있도록 화면을 제공할 것인지, 아니면 최초 플랫폼 사용시 자동으로 이 값을 생성할 것인지 결정해야 함.
     */
    @Column(name = "secret_key", columnDefinition = "VARCHAR(100)", nullable = true)
    String secretKey;

    /**
     * Secret Key를 생성하기 위한 UUID
     */
    @Column(name = "uuid", columnDefinition = "VARCHAR(100)", nullable = true)
    String uuid;

    /**
     * Key의 상태 코드
     */
    @Column(name = "status", columnDefinition = "VARCHAR(100)", nullable = true)
    @Enumerated(EnumType.STRING)
    AccessKeyStatus status;

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
