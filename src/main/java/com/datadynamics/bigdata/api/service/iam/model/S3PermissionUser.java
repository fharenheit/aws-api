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
@Entity(name = "api_iam_permission_user")

public class S3PermissionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    String username;

    @Column(name = "object_read")
    boolean objectRead;

    @Column(name = "object_execute")
    boolean objectExecute;

    @Column(name = "object_write")
    boolean objectWrite;

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
