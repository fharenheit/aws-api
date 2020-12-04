package com.datadynamics.bigdata.api.service.iam.model;

import com.datadynamics.bigdata.api.model.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccessKeyStatus implements BaseEnumCode<String> {

    Active("Active"),
    Inactive("Inactive");

    private final String value;

}
