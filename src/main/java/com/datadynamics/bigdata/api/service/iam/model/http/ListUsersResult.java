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
public class ListUsersResult {

    @XmlElement(name = "Users")
    private Users users;

    @XmlElement(name = "IsTruncated")
    private Boolean isTruncated;

    @XmlElement(name = "Maker")
    private String marker;

}
