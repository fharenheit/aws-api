package com.datadynamics.bigdata.api.service.iam.model.http.key;

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
public class ListAccessKeysResult {

    @XmlElement(name = "UserName")
    private String userName;

    @XmlElement(name = "AccessKeyMetadata")
    private AccessKeyMetadata accessKeyMetadata;

    @XmlElement(name = "IsTruncated")
    private Boolean isTruncated;

    @XmlElement(name = "Maker")
    private String maker;

}
