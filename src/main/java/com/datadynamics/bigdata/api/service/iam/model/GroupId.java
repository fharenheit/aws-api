package com.datadynamics.bigdata.api.service.iam.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Embeddable
public class GroupId implements Serializable {

    @Id
    @Column(name = "group_name", columnDefinition = "VARCHAR(100)")
    String groupName;

    @Id
    @Column(name = "path", columnDefinition = "VARCHAR(255)")
    String path;

}
