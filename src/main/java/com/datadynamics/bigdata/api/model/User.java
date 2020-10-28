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

    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(20)", nullable = false)
    String username;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    String name;

    @Column(name = "group_code", columnDefinition = "VARCHAR(100)")
    String groupCode;

    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    String email;

    @Column(name = "telephone", columnDefinition = "VARCHAR(100)")
    String telephone;

    @Column(name = "mobile", columnDefinition = "VARCHAR(100)")
    String mobile;

    @CreationTimestamp
    @Column(name = "create_ts", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_ts", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
