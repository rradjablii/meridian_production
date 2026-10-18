package com.rradjabli.meridian.common;

import org.springframework.beans.BeanUtils;

public final class GlobalMapper {

    private GlobalMapper(){}

    public static <T> T map(Object source, T targetClass){
        BeanUtils.copyProperties(source, targetClass);
        return targetClass;
    }

}
