package com.datadynamics.bigdata.api.service.iam.model.http;

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
@XmlRootElement(name = "ListUsersResponse")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ListUsersResponse {

    @XmlElement(name = "ListUsersResult")
    ListUsersResult listUsersResult;

    @XmlElement(name = "ResponseMetadata")
    ResponseMetadata responseMetadata;

}
