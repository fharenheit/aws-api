package com.datadynamics.bigdata.api.shared;

import java.util.Collection;

/**
 *
 */
public class CollectionUtils {

    public static Collection toCollection(Object... objects) {
        return org.springframework.util.CollectionUtils.arrayToList(objects);
    }


}
