package com.datadynamics.bigdata.api.util.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Map;

public class Grant {

    @JacksonXmlProperty(localName = "Grantee")
    protected Map grantee;

    @JacksonXmlProperty(localName = "Permission")
    protected String permission;

    public Map getGrantee() {
        return grantee;
    }

    public void setGrantee(Map value) {
        this.grantee = value;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String value) {
        this.permission = value;
    }

    @Override
    public String toString() {
        return "Grant{" +
                "grantee=" + grantee +
                ", permission='" + permission + '\'' +
                '}';
    }
}
