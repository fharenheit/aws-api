//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.3.2 버전을 통해 생성되었습니다. 
// <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2020.11.14 시간 02:17:24 PM KST 
//


package com.datadynamics.bigdata.api.service.s3.model.http;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="GetMetadata" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="GetData" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="InlineData" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ByteRangeStart" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="ByteRangeEnd" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="IfModifiedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="IfUnmodifiedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="IfMatch" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100" minOccurs="0"/&gt;
 *         &lt;element name="IfNoneMatch" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100" minOccurs="0"/&gt;
 *         &lt;element name="ReturnCompleteObjectOnConditionFailure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
        "key",
        "getMetadata",
        "getData",
        "inlineData",
        "byteRangeStart",
        "byteRangeEnd",
        "ifModifiedSince",
        "ifUnmodifiedSince",
        "ifMatch",
        "ifNoneMatch",
        "returnCompleteObjectOnConditionFailure",
        "awsAccessKeyId",
        "timestamp",
        "signature",
        "credential"
})
@XmlRootElement(name = "GetObjectExtended")
public class GetObjectExtended {

    @XmlElement(name = "Bucket", required = true)
    protected String bucket;
    @XmlElement(name = "Key", required = true)
    protected String key;
    @XmlElement(name = "GetMetadata")
    protected boolean getMetadata;
    @XmlElement(name = "GetData")
    protected boolean getData;
    @XmlElement(name = "InlineData")
    protected boolean inlineData;
    @XmlElement(name = "ByteRangeStart")
    protected Long byteRangeStart;
    @XmlElement(name = "ByteRangeEnd")
    protected Long byteRangeEnd;
    @XmlElement(name = "IfModifiedSince")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ifModifiedSince;
    @XmlElement(name = "IfUnmodifiedSince")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ifUnmodifiedSince;
    @XmlElement(name = "IfMatch")
    protected List<String> ifMatch;
    @XmlElement(name = "IfNoneMatch")
    protected List<String> ifNoneMatch;
    @XmlElement(name = "ReturnCompleteObjectOnConditionFailure")
    protected Boolean returnCompleteObjectOnConditionFailure;
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
     * key 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getKey() {
        return key;
    }

    /**
     * key 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * getMetadata 속성의 값을 가져옵니다.
     */
    public boolean isGetMetadata() {
        return getMetadata;
    }

    /**
     * getMetadata 속성의 값을 설정합니다.
     */
    public void setGetMetadata(boolean value) {
        this.getMetadata = value;
    }

    /**
     * getData 속성의 값을 가져옵니다.
     */
    public boolean isGetData() {
        return getData;
    }

    /**
     * getData 속성의 값을 설정합니다.
     */
    public void setGetData(boolean value) {
        this.getData = value;
    }

    /**
     * inlineData 속성의 값을 가져옵니다.
     */
    public boolean isInlineData() {
        return inlineData;
    }

    /**
     * inlineData 속성의 값을 설정합니다.
     */
    public void setInlineData(boolean value) {
        this.inlineData = value;
    }

    /**
     * byteRangeStart 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getByteRangeStart() {
        return byteRangeStart;
    }

    /**
     * byteRangeStart 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setByteRangeStart(Long value) {
        this.byteRangeStart = value;
    }

    /**
     * byteRangeEnd 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getByteRangeEnd() {
        return byteRangeEnd;
    }

    /**
     * byteRangeEnd 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setByteRangeEnd(Long value) {
        this.byteRangeEnd = value;
    }

    /**
     * ifModifiedSince 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getIfModifiedSince() {
        return ifModifiedSince;
    }

    /**
     * ifModifiedSince 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setIfModifiedSince(XMLGregorianCalendar value) {
        this.ifModifiedSince = value;
    }

    /**
     * ifUnmodifiedSince 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getIfUnmodifiedSince() {
        return ifUnmodifiedSince;
    }

    /**
     * ifUnmodifiedSince 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setIfUnmodifiedSince(XMLGregorianCalendar value) {
        this.ifUnmodifiedSince = value;
    }

    /**
     * Gets the value of the ifMatch property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifMatch property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIfMatch().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getIfMatch() {
        if (ifMatch == null) {
            ifMatch = new ArrayList<String>();
        }
        return this.ifMatch;
    }

    /**
     * Gets the value of the ifNoneMatch property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifNoneMatch property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIfNoneMatch().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getIfNoneMatch() {
        if (ifNoneMatch == null) {
            ifNoneMatch = new ArrayList<String>();
        }
        return this.ifNoneMatch;
    }

    /**
     * returnCompleteObjectOnConditionFailure 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isReturnCompleteObjectOnConditionFailure() {
        return returnCompleteObjectOnConditionFailure;
    }

    /**
     * returnCompleteObjectOnConditionFailure 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setReturnCompleteObjectOnConditionFailure(Boolean value) {
        this.returnCompleteObjectOnConditionFailure = value;
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
