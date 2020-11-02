package com.datadynamics.bigdata.api.service.s3.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Owner {

    @XmlElement(name = "DisplayName")
    private String displayName;

    @XmlElement(name = "ID")
    private String id;

}
