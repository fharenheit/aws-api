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
public class User {

    @XmlElement(name = "Path")
    private String path;

    @XmlElement(name = "UserName")
    private String userName;

    @XmlElement(name = "UserId")
    private String userId;

    @XmlElement(name = "Arn")
    private String arn;

    @XmlElement(name = "CreateDate")
    private java.util.Date createDate;

    @XmlElement(name = "PasswordLastUsed")
    private java.util.Date passwordLastUsed;

/*
    @XmlElement(name = "PermissionsBoundary")
    private AttachedPermissionsBoundary permissionsBoundary;

    @XmlElement(name = "Tags")
    private com.amazonaws.internal.SdkInternalList<Tag> tags;
*/
}
