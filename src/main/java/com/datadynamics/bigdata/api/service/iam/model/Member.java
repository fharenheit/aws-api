package com.datadynamics.bigdata.api.service.iam.model;

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
public class Member {

    @XmlElement(name = "UserName")
    private String userName;

    @XmlElement(name = "AccessKeyId")
    private String accessKeyId;

    @XmlElement(name = "Status")
    private String status;

    @XmlElement(name = "CreateDate")
    private java.util.Date createDate;
}
