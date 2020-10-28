package com.datadynamics.bigdata.api.model;

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
@Entity(name = "api_iam_permission_group")
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "group_code", columnDefinition = "VARCHAR(100)", nullable = false)
    String groupCode;


    @Column(name = "object_read")
    boolean objectRead;

    @Column(name = "object_execute")
    boolean objectExecute;

    @Column(name = "object_write")
    boolean objectWrite;

    @Column(name = "sql_select")
    boolean select;

    @Column(name = "sql_update")
    boolean update;

    @Column(name = "sql_drop")
    boolean drop;

    @Column(name = "sql_create")
    boolean create;

    @CreationTimestamp
    @Column(name = "create_ts", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_ts", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
