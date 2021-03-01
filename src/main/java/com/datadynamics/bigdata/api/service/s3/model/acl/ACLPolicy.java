package com.datadynamics.bigdata.api.service.s3.model.acl;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "api_s3_acl_policy")
public class ACLPolicy {

    @Id
    @Column(name = "uuid", columnDefinition = "VARCHAR(255)", unique = true)
    String uuid;

    @Column(name = "bucket_name", columnDefinition = "VARCHAR(255)")
    String bucketName;

    @Column(name = "key", columnDefinition = "VARCHAR(255)")
    String key;

    @Column(name = "username", columnDefinition = "VARCHAR(100)")
    String username;

    @Column(name = "owner_name", columnDefinition = "VARCHAR(100)")
    String ownerName;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "acl_policy_document")
    String policyDocument;

    /**
     * 생성일 (이 필드에는 값을 입력하지 않아도 Hibernate가 INSERT시 자동으로 기록)
     */
    @CreationTimestamp
    @Column(name = "create_ts", insertable = true, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    /**
     * 변경일 (이 필드에는 값을 입력하지 않아도 Hibernate가 UPDATE시 자동으로 기록)
     */
    @UpdateTimestamp
    @Column(name = "update_ts", insertable = true, updatable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
