package com.datadynamics.bigdata.api.service.iam.logging;

import com.datadynamics.bigdata.api.model.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * IAM에 대해서 API 호출시 사용자가 어떤 행위를 했는지를 기록하기 위해서 필요한 코드.
 * 코드는 IAM API에 맞추어서 변경하도록 한다.
 */
@Getter
@AllArgsConstructor
public enum IamAccessTypeEnum implements BaseEnumCode<String> {

    CREATE_ACCESS_KEY("CREATE_ACCESS_KEY"),
    CREATE_GROUP("CREATE_GROUP"),
    CREATE_USER("CREATE_USER"),
    CREATE_POLICY("CREATE_POLICY"),
    CHANGE_PASSWORD("CHANGE_PASSWORD"),
    DELETE_GROUP("DELETE_GROUP"),
    DELETE_USER("DELETE_USER"),
    DELETE_ACCESS_KEY("DELETE_ACCESS_KEY"),
    DELETE_POLICY("DELETE_POLICY"),
    DELETE_ROLE("DELETE_ROLE"),
    GET_GROUP("GET_GROUP"),
    GET_USER("GET_USER"),
    GET_ROLE("GET_ROLE"),
    GET_POLICY("GET_POLICY"),
    LIST_GROUPS("LIST_GROUPS"),
    LIST_USERS("LIST_USERS"),
    LIST_POLICIES("LIST_POLICIES"),
    LIST_ROLES("LIST_ROLES"),
    UPDATE_GROUP("UPDATE_GROUP"),
    UPDATE_USER("UPDATE_USER"),
    UPDATE_POLICY("UPDATE_POLICY"),
    UPDATE_ROLE("UPDATE_ROLE");

    private final String value;

}
