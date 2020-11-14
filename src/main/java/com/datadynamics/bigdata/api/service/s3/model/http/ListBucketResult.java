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
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ListBucketResult complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="ListBucketResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Metadata" type="{http://s3.amazonaws.com/doc/2006-03-01/}MetadataEntry" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Prefix" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Marker" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="NextMarker" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MaxKeys" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Delimiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IsTruncated" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Contents" type="{http://s3.amazonaws.com/doc/2006-03-01/}ListEntry" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CommonPrefixes" type="{http://s3.amazonaws.com/doc/2006-03-01/}PrefixEntry" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListBucketResult", propOrder = {
    "metadata",
    "name",
    "prefix",
    "marker",
    "nextMarker",
    "maxKeys",
    "delimiter",
    "isTruncated",
    "contents",
    "commonPrefixes"
})
public class ListBucketResult {

    @XmlElement(name = "Metadata")
    protected List<MetadataEntry> metadata;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Prefix", required = true)
    protected String prefix;
    @XmlElement(name = "Marker", required = true)
    protected String marker;
    @XmlElement(name = "NextMarker")
    protected String nextMarker;
    @XmlElement(name = "MaxKeys")
    protected int maxKeys;
    @XmlElement(name = "Delimiter")
    protected String delimiter;
    @XmlElement(name = "IsTruncated")
    protected boolean isTruncated;
    @XmlElement(name = "Contents")
    protected List<ListEntry> contents;
    @XmlElement(name = "CommonPrefixes")
    protected List<PrefixEntry> commonPrefixes;

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
     * name 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * prefix 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * prefix 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix(String value) {
        this.prefix = value;
    }

    /**
     * marker 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarker() {
        return marker;
    }

    /**
     * marker 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarker(String value) {
        this.marker = value;
    }

    /**
     * nextMarker 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextMarker() {
        return nextMarker;
    }

    /**
     * nextMarker 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextMarker(String value) {
        this.nextMarker = value;
    }

    /**
     * maxKeys 속성의 값을 가져옵니다.
     * 
     */
    public int getMaxKeys() {
        return maxKeys;
    }

    /**
     * maxKeys 속성의 값을 설정합니다.
     * 
     */
    public void setMaxKeys(int value) {
        this.maxKeys = value;
    }

    /**
     * delimiter 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * delimiter 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelimiter(String value) {
        this.delimiter = value;
    }

    /**
     * isTruncated 속성의 값을 가져옵니다.
     * 
     */
    public boolean isIsTruncated() {
        return isTruncated;
    }

    /**
     * isTruncated 속성의 값을 설정합니다.
     * 
     */
    public void setIsTruncated(boolean value) {
        this.isTruncated = value;
    }

    /**
     * Gets the value of the contents property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contents property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListEntry }
     * 
     * 
     */
    public List<ListEntry> getContents() {
        if (contents == null) {
            contents = new ArrayList<ListEntry>();
        }
        return this.contents;
    }

    /**
     * Gets the value of the commonPrefixes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commonPrefixes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommonPrefixes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrefixEntry }
     * 
     * 
     */
    public List<PrefixEntry> getCommonPrefixes() {
        if (commonPrefixes == null) {
            commonPrefixes = new ArrayList<PrefixEntry>();
        }
        return this.commonPrefixes;
    }

}
