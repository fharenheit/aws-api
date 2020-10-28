package com.datadynamics.bigdata.api.service.iam.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@XmlRootElement(name = "ListAccessKeysResponse", namespace = "https://iam.amazonaws.com/doc/2010-05-08/")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ListAccessKeysResponse {

    @XmlElement(name = "ListAccessKeysResult")
    ListAccessKeysResult listAccessKeysResult;

    @XmlElement(name = "ResponseMetadata")
    ResponseMetadata responseMetadata;

}
