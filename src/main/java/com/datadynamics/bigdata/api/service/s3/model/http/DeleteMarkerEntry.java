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
 * <p>DeleteMarkerEntry complex type에 대한 Java 클래스입니다.
 *
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType name="DeleteMarkerEntry"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="VersionId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IsLatest" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="LastModified" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="Owner" type="{http://s3.amazonaws.com/doc/2006-03-01/}CanonicalUser" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteMarkerEntry", propOrder = {
        "key",
        "versionId",
        "isLatest",
        "lastModified",
        "owner"
})
public class DeleteMarkerEntry {

    @XmlElement(name = "Key", required = true)
    protected String key;
    @XmlElement(name = "VersionId", required = true)
    protected String versionId;
    @XmlElement(name = "IsLatest")
    protected boolean isLatest;
    @XmlElement(name = "LastModified", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModified;
    @XmlElement(name = "Owner")
    protected CanonicalUser owner;

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
     * versionId 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * versionId 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersionId(String value) {
        this.versionId = value;
    }

    /**
     * isLatest 속성의 값을 가져옵니다.
     */
    public boolean isIsLatest() {
        return isLatest;
    }

    /**
     * isLatest 속성의 값을 설정합니다.
     */
    public void setIsLatest(boolean value) {
        this.isLatest = value;
    }

    /**
     * lastModified 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getLastModified() {
        return lastModified;
    }

    /**
     * lastModified 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setLastModified(XMLGregorianCalendar value) {
        this.lastModified = value;
    }

    /**
     * owner 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link CanonicalUser }
     */
    public CanonicalUser getOwner() {
        return owner;
    }

    /**
     * owner 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link CanonicalUser }
     */
    public void setOwner(CanonicalUser value) {
        this.owner = value;
    }

}
