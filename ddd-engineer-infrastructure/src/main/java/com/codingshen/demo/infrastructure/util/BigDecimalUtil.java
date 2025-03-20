package com.codingshen.demo.infrastructure.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Objects;

public class BigDecimalUtil {

    private static final int ITEM_BASE = 1000000;
    private static final BigDecimal ITEM_BASE_BIG_DECIMAL = BigDecimal.valueOf(ITEM_BASE);

    public static BigDecimal longDivideBase2BigDecimal(Long value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return BigDecimal.valueOf(value).divide(ITEM_BASE_BIG_DECIMAL, 2, RoundingMode.DOWN);
    }

    public static BigDecimal getBigDecimalFromLong(Long value, int scale) {
        if (Objects.isNull(value)) {
            return null;
        }
        return BigDecimal.valueOf(value).divide(ITEM_BASE_BIG_DECIMAL, scale, RoundingMode.DOWN);
    }

    public static Long bigDecimalMutiplyBase2Long(BigDecimal value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return value.multiply(ITEM_BASE_BIG_DECIMAL).longValue();
    }

    public static BigDecimal zeroIfNull(BigDecimal bigDecimal) {
        if (Objects.isNull(bigDecimal)) {
            return BigDecimal.ZERO;
        }
        return bigDecimal;
    }

    public static BigDecimal percentBigDecimal(BigDecimal a, BigDecimal b, BigDecimal amount) {
        return amount.multiply(a).divide(b, 2, RoundingMode.DOWN);
    }

}
