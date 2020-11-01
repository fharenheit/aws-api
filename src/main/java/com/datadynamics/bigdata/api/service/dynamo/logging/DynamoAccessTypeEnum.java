package com.datadynamics.bigdata.api.service.dynamo.logging;

import com.datadynamics.bigdata.api.model.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Dynamo에 대해서 API 호출시 사용자가 어떤 행위를 했는지를 기록하기 위해서 필요한 코드.
 * 코드는 Dynamo API에 맞추어서 변경하도록 한다.
 */
@Getter
@AllArgsConstructor
public enum DynamoAccessTypeEnum implements BaseEnumCode<String> {

    CREATE("CREATE"),
    DELETE("DELETE"),
    LIST("LIST"),
    GET("GET"),
    DESCRIBE("DESCRIBE"),
    QUERY("QUERY"),
    SCAN("SCAN"),
    UPDATE("UPDATE");

    private final String value;

}
