package com.datadynamics.bigdata.api.service.iam.model.http;

import com.amazonaws.services.identitymanagement.model.ListGroupsResult;
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
@XmlRootElement(name = "ListGroupsResponse")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ListGroupsResponse {

    @XmlElement(name = "ListGroupsResult")
    ListGroupsResult listGroupsResult;

    @XmlElement(name = "ResponseMetadata")
    ResponseMetadata responseMetadata;

}
