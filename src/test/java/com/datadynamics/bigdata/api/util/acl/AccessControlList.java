package com.datadynamics.bigdata.api.util.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class AccessControlList {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Grant")
    protected List<Grant> grant;

    public List<Grant> getGrant() {
        if (grant == null) {
            grant = new ArrayList<Grant>();
        }
        return this.grant;
    }

    @Override
    public String toString() {
        return "AccessControlList{" +
                "grant=" + grant +
                '}';
    }
}
