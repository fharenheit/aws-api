package com.datadynamics.bigdata.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataSourceTypeEnum implements BaseEnumCode<String> {

    GREENPLUM("Greenplum"),
    ORACLE("Oracle");

    private final String value;

}
