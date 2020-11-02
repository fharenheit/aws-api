package com.datadynamics.bigdata.api.service.s3.model.http;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Bucket {

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "CreationDate")
    private Timestamp creationDate;

}
