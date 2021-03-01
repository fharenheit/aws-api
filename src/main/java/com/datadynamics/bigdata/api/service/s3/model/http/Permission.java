package com.datadynamics.bigdata.api.service.s3.model.http;

public enum Permission {

    READ,
    WRITE,
    READ_ACP,
    WRITE_ACP,
    FULL_CONTROL;

    public String value() {
        return name();
    }

    public static Permission fromValue(String v) {
        return valueOf(v);
    }

}
