package com.datadynamics.bigdata.api.service.iam.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static Map<String, String> map(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
