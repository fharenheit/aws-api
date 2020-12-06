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
public class Groups {

    @XmlElement(name = "member")
    private List<Group> members;


    public java.util.List<Group> getMembers() {
        if (members == null) {
            members = new ArrayList<Group>();
        }
        return members;
    }

    public void setMembers(java.util.List<Group> members) {
        if (members == null) {
            this.members = null;
            return;
        }
        this.members = new ArrayList<Group>(members);
    }
}
