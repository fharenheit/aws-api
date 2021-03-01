package com.datadynamics.bigdata.api.service.s3.model.acl;

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
@Entity(name = "api_s3_acl_policy_detail")
@Table(indexes = {
        @Index(name = "IDX_TYPE_POLICY_DETAIL", unique = false, columnList = "type"),
        @Index(name = "IDX_PERMISSION_POLICY_DETAIL", unique = false, columnList = "permission"),
        @Index(name = "IDX_OWNER_POLICY_DETAIL", unique = false, columnList = "owner_name"),
        @Index(name = "IDX_UUID_POLICY_DETAIL", unique = false, columnList = "uuid"),
        @Index(name = "IDX_USERNAME_POLICY_DETAIL", unique = false, columnList = "username")
})
public class ACLPolicyDetail {

    @Column(name = "uuid", columnDefinition = "VARCHAR(255)", unique = false)
    String uuid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "policy_detail_id")
    Integer policyDetailId;

    @Column(name = "username", columnDefinition = "VARCHAR(100)")
    String username;

    @Column(name = "owner_name", columnDefinition = "VARCHAR(100)")
    String ownerName;

    @Column(name = "display_name", columnDefinition = "VARCHAR(255)", nullable = true)
    String displayName;

    @Column(name = "email_address", columnDefinition = "VARCHAR(255)", nullable = true)
    String emailAddress;

    @Column(name = "type", columnDefinition = "VARCHAR(255)", nullable = false)
    String type;

    @Column(name = "uri", columnDefinition = "VARCHAR(255)", nullable = true)
    String uri;

    @Column(name = "permission", columnDefinition = "VARCHAR(255)", nullable = false)
    String permission;

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