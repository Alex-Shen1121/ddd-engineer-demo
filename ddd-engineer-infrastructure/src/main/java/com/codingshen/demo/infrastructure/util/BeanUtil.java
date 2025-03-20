package com.codingshen.demo.infrastructure.util;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * BeanUtil
 *
 * @author chenjizhou02
 * @date 2024/5/7
 **/
public class BeanUtil {
    public BeanUtil() {
    }

    public static void copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (InvocationTargetException | IllegalAccessException var3) {
            throw new IllegalArgumentException("copy bean properties error", var3);
        }
    }
}
