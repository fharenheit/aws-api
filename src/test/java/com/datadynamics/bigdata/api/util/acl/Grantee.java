package com.datadynamics.bigdata.api.util.acl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class Grantee {

    @JsonProperty("ID")
    protected String id;

    @JsonProperty("DisplayName")
    protected String displayName;

    protected String type;

    @JsonProperty("URI")
    protected String uri;

    @JsonProperty("EmailAddress")
    protected String emailAddress;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String value) {
        this.displayName = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Grantee{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
