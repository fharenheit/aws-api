//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.3.2 버전을 통해 생성되었습니다. 
// <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2020.11.14 시간 02:17:24 PM KST 
//


package com.datadynamics.bigdata.api.service.s3.model.http;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 *
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Bucket" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AWSAccessKeyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Signature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Credential" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "bucket",
        "awsAccessKeyId",
        "timestamp",
        "signature",
        "credential"
})
@XmlRootElement(name = "DeleteBucket")
public class DeleteBucket {

    @XmlElement(name = "Bucket", required = true)
    protected String bucket;
    @XmlElement(name = "AWSAccessKeyId")
    protected String awsAccessKeyId;
    @XmlElement(name = "Timestamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(name = "Signature")
    protected String signature;
    @XmlElement(name = "Credential")
    protected String credential;

    /**
     * bucket 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * bucket 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBucket(String value) {
        this.bucket = value;
    }

    /**
     * awsAccessKeyId 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAWSAccessKeyId() {
        return awsAccessKeyId;
    }

    /**
     * awsAccessKeyId 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAWSAccessKeyId(String value) {
        this.awsAccessKeyId = value;
    }

    /**
     * timestamp 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * timestamp 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * signature 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSignature() {
        return signature;
    }

    /**
     * signature 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * credential 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCredential() {
        return credential;
    }

    /**
     * credential 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCredential(String value) {
        this.credential = value;
    }

}
