package com.datadynamics.bigdata.api.service.iam.model.http;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AccessKeyMetadata implements Serializable, Cloneable {

    @XmlElement(name = "member")
    private List<Member> members;

}