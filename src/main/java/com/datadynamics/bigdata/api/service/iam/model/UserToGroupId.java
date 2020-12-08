package com.datadynamics.bigdata.api.service.iam.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Embeddable
public class UserToGroupId implements Serializable {

    @Column(name = "user_path", columnDefinition = "VARCHAR(255)")
    private String userPath;

    @Column(name = "username", columnDefinition = "VARCHAR(100)", nullable = false)
    private String userName;

    @Column(name = "group_path", columnDefinition = "VARCHAR(255)")
    private String groupPath;

    @Column(name = "group_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String groupName;

}
