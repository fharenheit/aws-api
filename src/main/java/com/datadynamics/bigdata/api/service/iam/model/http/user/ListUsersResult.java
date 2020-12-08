package com.datadynamics.bigdata.api.service.iam.model.http.user;

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
public class ListUsersResult {

    @XmlElement(name = "Users")
    private Users users;

    @XmlElement(name = "IsTruncated")
    private Boolean isTruncated;

    @XmlElement(name = "Maker")
    private String marker;

    public Users getUsers() {
        if (this.users == null) {
            this.users = new Users();
        }
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
