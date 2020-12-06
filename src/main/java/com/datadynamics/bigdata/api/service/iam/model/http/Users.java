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
public class Users {

    @XmlElement(name = "member")
    private List<User> members;

    public List<User> getMembers() {
        if (members == null) {
            members = new ArrayList<User>();
        }
        return members;
    }

    public void setMembers(List<User> members) {
        if (members == null) {
            this.members = null;
            return;
        }
        this.members = new ArrayList<User>(members);
    }
}
