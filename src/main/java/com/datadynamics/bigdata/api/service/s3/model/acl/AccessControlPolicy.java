package com.datadynamics.bigdata.api.service.s3.model.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(namespace = "http://s3.amazonaws.com/doc/2006-03-01/")
public class AccessControlPolicy {

    @JacksonXmlProperty(localName = "Owner")
    protected Owner owner;

    @JacksonXmlProperty(localName = "AccessControlList")
    protected AccessControlList accessControlList;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner value) {
        this.owner = value;
    }

    public AccessControlList getAccessControlList() {
        return accessControlList;
    }

    public void setAccessControlList(AccessControlList value) {
        this.accessControlList = value;
    }

}
