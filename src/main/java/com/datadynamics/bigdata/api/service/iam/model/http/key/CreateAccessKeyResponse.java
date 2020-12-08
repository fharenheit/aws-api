package com.datadynamics.bigdata.api.service.iam.model.http.key;

import com.datadynamics.bigdata.api.service.iam.model.http.ResponseMetadata;
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
@XmlRootElement(name = "CreateAccessKeyResponse")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CreateAccessKeyResponse {

    @XmlElement(name = "CreateAccessKeyResult")
    CreateAccessKeyResult createAccessKeyResult;

    @XmlElement(name = "ResponseMetadata")
    ResponseMetadata responseMetadata;

}
