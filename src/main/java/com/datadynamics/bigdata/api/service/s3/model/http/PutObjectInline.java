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
 *         &lt;element name="Metadata" type="{http://s3.amazonaws.com/doc/2006-03-01/}MetadataEntry" maxOccurs="100" minOccurs="0"/&gt;
 *         &lt;element name="Data" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="ContentLength" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="AccessControlList" type="{http://s3.amazonaws.com/doc/2006-03-01/}AccessControlList" minOccurs="0"/&gt;
 *         &lt;element name="StorageClass" type="{http://s3.amazonaws.com/doc/2006-03-01/}StorageClass" minOccurs="0"/&gt;
 *         &lt;element name="AWSAccessKeyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Signature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Credential" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bucket",
    "key",
    "metadata",
    "data",
    "contentLength",
    "accessControlList",
    "storageClass",
    "awsAccessKeyId",
    "timestamp",
    "signature",
    "credential"
})
@XmlRootElement(name = "PutObjectInline")
public class PutObjectInline {

    @XmlElement(name = "Bucket", required = true)
    protected String bucket;
    @XmlElement(name = "Key", required = true)
    protected String key;
    @XmlElement(name = "Metadata")
    protected List<MetadataEntry> metadata;
    @XmlElement(name = "Data", required = true)
    protected byte[] data;
    @XmlElement(name = "ContentLength")
    protected long contentLength;
    @XmlElement(name = "AccessControlList")
    protected AccessControlList accessControlList;
    @XmlElement(name = "StorageClass")
    @XmlSchemaType(name = "string")
    protected StorageClass storageClass;
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
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * bucket 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBucket(String value) {
        this.bucket = value;
    }

    /**
     * key 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * key 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the metadata property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadata property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadataEntry }
     * 
     * 
     */
    public List<MetadataEntry> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<MetadataEntry>();
        }
        return this.metadata;
    }

    /**
     * data 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getData() {
        return data;
    }

    /**
     * data 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setData(byte[] value) {
        this.data = value;
    }

    /**
     * contentLength 속성의 값을 가져옵니다.
     * 
     */
    public long getContentLength() {
        return contentLength;
    }

    /**
     * contentLength 속성의 값을 설정합니다.
     * 
     */
    public void setContentLength(long value) {
        this.contentLength = value;
    }

    /**
     * accessControlList 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlList }
     *     
     */
    public AccessControlList getAccessControlList() {
        return accessControlList;
    }

    /**
     * accessControlList 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlList }
     *     
     */
    public void setAccessControlList(AccessControlList value) {
        this.accessControlList = value;
    }

    /**
     * storageClass 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link StorageClass }
     *     
     */
    public StorageClass getStorageClass() {
        return storageClass;
    }

    /**
     * storageClass 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link StorageClass }
     *     
     */
    public void setStorageClass(StorageClass value) {
        this.storageClass = value;
    }

    /**
     * awsAccessKeyId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAWSAccessKeyId() {
        return awsAccessKeyId;
    }

    /**
     * awsAccessKeyId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAWSAccessKeyId(String value) {
        this.awsAccessKeyId = value;
    }

    /**
     * timestamp 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * timestamp 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * signature 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignature() {
        return signature;
    }

    /**
     * signature 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * credential 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredential() {
        return credential;
    }

    /**
     * credential 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredential(String value) {
        this.credential = value;
    }

}
