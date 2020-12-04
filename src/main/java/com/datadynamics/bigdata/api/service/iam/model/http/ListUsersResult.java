package com.datadynamics.bigdata.api.service.iam.model.http;

import com.amazonaws.services.identitymanagement.model.User;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ListUsersResult {

    @XmlElement(name = "Users")
    private List<User> users;

    @XmlElement(name = "IsTruncated")
    private Boolean isTruncated;

    @XmlElement(name = "Maker")
    private String marker;

    @XmlElement(name = "Test")
    private String test = "asdfjlasdflasdfjl";

}
