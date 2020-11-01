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
@Entity(name = "api_iam_user")
public class User {

    /**
     * 사용자명
     */
    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(20)", nullable = false)
    String username;

    /**
     * 이름
     */
    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    String name;

    /**
     * 부서코드
     */
    @Column(name = "group_code", columnDefinition = "VARCHAR(100)")
    String groupCode;

    /**
     * 전자우편
     */
    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    String email;

    /**
     * 전화번호
     */
    @Column(name = "telephone", columnDefinition = "VARCHAR(100)")
    String telephone;

    /**
     * 핸드폰 번호
     */
    @Column(name = "mobile", columnDefinition = "VARCHAR(100)")
    String mobile;

    /**
     * 관리자 여부.
     * 관리자인 경우 S3, Dynamo 등에서 관리자가 할 수 있는 일(Delete Bucket) 등을 추가로 할 수 있으므로 필요함.
     */
    @Column(name = "is_admin", columnDefinition = "boolean default false")
    Boolean admin;

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
