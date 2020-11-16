//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.3.2 버전을 통해 생성되었습니다. 
// <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2020.11.14 시간 02:17:24 PM KST 
//


package com.datadynamics.bigdata.api.service.s3.model.http;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BucketLoggingStatus complex type에 대한 Java 클래스입니다.
 *
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType name="BucketLoggingStatus"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LoggingEnabled" type="{http://s3.amazonaws.com/doc/2006-03-01/}LoggingSettings" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BucketLoggingStatus", propOrder = {
        "loggingEnabled"
})
public class BucketLoggingStatus {

    @XmlElement(name = "LoggingEnabled")
    protected LoggingSettings loggingEnabled;

    /**
     * loggingEnabled 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link LoggingSettings }
     */
    public LoggingSettings getLoggingEnabled() {
        return loggingEnabled;
    }

    /**
     * loggingEnabled 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link LoggingSettings }
     */
    public void setLoggingEnabled(LoggingSettings value) {
        this.loggingEnabled = value;
    }

}
