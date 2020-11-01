package com.datadynamics.bigdata.api.service.s3.logging;

import com.datadynamics.bigdata.api.model.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * S3에 대해서 API 호출시 사용자가 어떤 행위를 했는지를 기록하기 위해서 필요한 코드.
 * 코드는 S3 API에 맞추어서 변경하도록 한다.
 */
@Getter
@AllArgsConstructor
public enum S3AccessTypeEnum implements BaseEnumCode<String> {

    CREATE("Create"),
    DELETE("Delete"),
    READ("Read"),
    UPDATE("Update");

    private final String value;

}
