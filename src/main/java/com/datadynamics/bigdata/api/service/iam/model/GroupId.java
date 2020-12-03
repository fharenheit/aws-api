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
public class GroupId implements Serializable {

    @Column(name = "group_name", columnDefinition = "VARCHAR(100)")
    String groupName;

    @Column(name = "path", columnDefinition = "VARCHAR(255)")
    String path;

}
