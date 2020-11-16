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
 *         &lt;element name="SourceBucket" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SourceKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DestinationBucket" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DestinationKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MetadataDirective" type="{http://s3.amazonaws.com/doc/2006-03-01/}MetadataDirective" minOccurs="0"/&gt;
 *         &lt;element name="Metadata" type="{http://s3.amazonaws.com/doc/2006-03-01/}MetadataEntry" maxOccurs="100" minOccurs="0"/&gt;
 *         &lt;element name="AccessControlList" type="{http://s3.amazonaws.com/doc/2006-03-01/}AccessControlList" minOccurs="0"/&gt;
 *         &lt;element name="CopySourceIfModifiedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="CopySourceIfUnmodifiedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="CopySourceIfMatch" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100" minOccurs="0"/&gt;
 *         &lt;element name="CopySourceIfNoneMatch" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100" minOccurs="0"/&gt;
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
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sourceBucket",
        "sourceKey",
        "destinationBucket",
        "destinationKey",
        "metadataDirective",
        "metadata",
        "accessControlList",
        "copySourceIfModifiedSince",
        "copySourceIfUnmodifiedSince",
        "copySourceIfMatch",
        "copySourceIfNoneMatch",
        "storageClass",
        "awsAccessKeyId",
        "timestamp",
        "signature",
        "credential"
})
@XmlRootElement(name = "CopyObject")
public class CopyObject {

    @XmlElement(name = "SourceBucket", required = true)
    protected String sourceBucket;
    @XmlElement(name = "SourceKey", required = true)
    protected String sourceKey;
    @XmlElement(name = "DestinationBucket", required = true)
    protected String destinationBucket;
    @XmlElement(name = "DestinationKey", required = true)
    protected String destinationKey;
    @XmlElement(name = "MetadataDirective")
    @XmlSchemaType(name = "string")
    protected MetadataDirective metadataDirective;
    @XmlElement(name = "Metadata")
    protected List<MetadataEntry> metadata;
    @XmlElement(name = "AccessControlList")
    protected AccessControlList accessControlList;
    @XmlElement(name = "CopySourceIfModifiedSince")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar copySourceIfModifiedSince;
    @XmlElement(name = "CopySourceIfUnmodifiedSince")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar copySourceIfUnmodifiedSince;
    @XmlElement(name = "CopySourceIfMatch")
    protected List<String> copySourceIfMatch;
    @XmlElement(name = "CopySourceIfNoneMatch")
    protected List<String> copySourceIfNoneMatch;
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
     * sourceBucket 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSourceBucket() {
        return sourceBucket;
    }

    /**
     * sourceBucket 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSourceBucket(String value) {
        this.sourceBucket = value;
    }

    /**
     * sourceKey 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSourceKey() {
        return sourceKey;
    }

    /**
     * sourceKey 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSourceKey(String value) {
        this.sourceKey = value;
    }

    /**
     * destinationBucket 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDestinationBucket() {
        return destinationBucket;
    }

    /**
     * destinationBucket 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDestinationBucket(String value) {
        this.destinationBucket = value;
    }

    /**
     * destinationKey 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDestinationKey() {
        return destinationKey;
    }

    /**
     * destinationKey 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDestinationKey(String value) {
        this.destinationKey = value;
    }

    /**
     * metadataDirective 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link MetadataDirective }
     */
    public MetadataDirective getMetadataDirective() {
        return metadataDirective;
    }

    /**
     * metadataDirective 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link MetadataDirective }
     */
    public void setMetadataDirective(MetadataDirective value) {
        this.metadataDirective = value;
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
     */
    public List<MetadataEntry> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<MetadataEntry>();
        }
        return this.metadata;
    }

    /**
     * accessControlList 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link AccessControlList }
     */
    public AccessControlList getAccessControlList() {
        return accessControlList;
    }

    /**
     * accessControlList 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link AccessControlList }
     */
    public void setAccessControlList(AccessControlList value) {
        this.accessControlList = value;
    }

    /**
     * copySourceIfModifiedSince 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getCopySourceIfModifiedSince() {
        return copySourceIfModifiedSince;
    }

    /**
     * copySourceIfModifiedSince 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCopySourceIfModifiedSince(XMLGregorianCalendar value) {
        this.copySourceIfModifiedSince = value;
    }

    /**
     * copySourceIfUnmodifiedSince 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getCopySourceIfUnmodifiedSince() {
        return copySourceIfUnmodifiedSince;
    }

    /**
     * copySourceIfUnmodifiedSince 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCopySourceIfUnmodifiedSince(XMLGregorianCalendar value) {
        this.copySourceIfUnmodifiedSince = value;
    }

    /**
     * Gets the value of the copySourceIfMatch property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copySourceIfMatch property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopySourceIfMatch().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getCopySourceIfMatch() {
        if (copySourceIfMatch == null) {
            copySourceIfMatch = new ArrayList<String>();
        }
        return this.copySourceIfMatch;
    }

    /**
     * Gets the value of the copySourceIfNoneMatch property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copySourceIfNoneMatch property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopySourceIfNoneMatch().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getCopySourceIfNoneMatch() {
        if (copySourceIfNoneMatch == null) {
            copySourceIfNoneMatch = new ArrayList<String>();
        }
        return this.copySourceIfNoneMatch;
    }

    /**
     * storageClass 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link StorageClass }
     */
    public StorageClass getStorageClass() {
        return storageClass;
    }

    /**
     * storageClass 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link StorageClass }
     */
    public void setStorageClass(StorageClass value) {
        this.storageClass = value;
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
