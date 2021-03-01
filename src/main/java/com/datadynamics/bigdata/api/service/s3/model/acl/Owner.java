package com.datadynamics.bigdata.api.service.s3.model.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Owner {

    @JacksonXmlProperty(localName = "ID")
    protected String id;

    @JacksonXmlProperty(localName = "DisplayName")
    protected String displayName;

    public String getID() {
        return id;
    }

    public void setID(String value) {
        this.id = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String value) {
        this.displayName = value;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
