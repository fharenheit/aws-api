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
public class Group {

    @XmlElement(name = "Path")
    private String path;

    @XmlElement(name = "GroupName")
    private String groupName;

    @XmlElement(name = "GroupId")
    private String groupId;

    @XmlElement(name = "Arn")
    private String arn;

    @XmlElement(name = "CreateDate")
    private java.util.Date createDate;

}
