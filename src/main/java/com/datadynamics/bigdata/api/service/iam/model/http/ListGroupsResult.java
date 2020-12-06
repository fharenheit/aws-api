package com.datadynamics.bigdata.api.service.iam.model.http;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ListGroupsResult {

    @XmlElement(name = "Groups")
    private Groups groups;

    @XmlElement(name = "IsTruncated")
    private Boolean isTruncated;

    @XmlElement(name = "Marker")
    private String marker;

    public Groups getGroups() {
        if (this.groups == null) {
            this.groups = new Groups();
        }
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
