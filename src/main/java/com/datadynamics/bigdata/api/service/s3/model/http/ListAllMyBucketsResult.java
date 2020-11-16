package com.datadynamics.bigdata.api.service.s3.model.http;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ListAllMyBucketsResult complex type에 대한 Java 클래스입니다.
 *
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType name="ListAllMyBucketsResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Owner" type="{http://s3.amazonaws.com/doc/2006-03-01/}CanonicalUser"/&gt;
 *         &lt;element name="Buckets" type="{http://s3.amazonaws.com/doc/2006-03-01/}ListAllMyBucketsList"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListAllMyBucketsResult", propOrder = {
        "owner",
        "buckets"
})
public class ListAllMyBucketsResult {

    @XmlElement(name = "Owner", required = true)
    protected CanonicalUser owner;
    @XmlElement(name = "Buckets", required = true)
    protected ListAllMyBucketsList buckets;

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

    /**
     * buckets 속성의 값을 가져옵니다.
     *
     * @return possible object is
     * {@link ListAllMyBucketsList }
     */
    public ListAllMyBucketsList getBuckets() {
        return buckets;
    }

    /**
     * buckets 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link ListAllMyBucketsList }
     */
    public void setBuckets(ListAllMyBucketsList value) {
        this.buckets = value;
    }

}
