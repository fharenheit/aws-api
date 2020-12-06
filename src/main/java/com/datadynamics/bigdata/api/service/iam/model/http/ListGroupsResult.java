package com.datadynamics.bigdata.api.service.iam.model.http;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ListGroupsResult {

    @XmlElement(name = "Groups")
    private List<Group> groups;

    @XmlElement(name = "IsTruncated")
    private Boolean isTruncated;

    @XmlElement(name = "Marker")
    private String marker;

    public java.util.List<Group> getGroups() {
        if (groups == null) {
            groups = new ArrayList<Group>();
        }
        return groups;
    }

    public void setGroups(java.util.List<Group> groups) {
        if (groups == null) {
            this.groups = null;
            return;
        }
        this.groups = new ArrayList<Group>(groups);
    }

}
