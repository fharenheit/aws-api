package com.datadynamics.bigdata.api.service.iam.model.http;

import com.amazonaws.services.identitymanagement.model.GetUserResult;
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
@XmlRootElement(name = "GetUserResponse", namespace = "https://iam.amazonaws.com/doc/2010-05-08/")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class GetUserResponse {

    @XmlElement(name = "GetUserResult")
    GetUserResult getUserResult;

    @XmlElement(name = "ResponseMetadata")
    ResponseMetadata responseMetadata;

}
