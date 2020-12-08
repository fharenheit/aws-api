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
@XmlRootElement(name = "DeleteAccessKeyResponse", namespace = "https://iam.amazonaws.com/doc/2010-05-08/")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class DeleteAccessKeyResponse {

    @XmlElement(name = "ResponseMetadata")
    ResponseMetadata responseMetadata;

}
