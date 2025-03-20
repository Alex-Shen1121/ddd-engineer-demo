package com.codingshen.demo.infrastructure.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DataConvertUtils {
    @Deprecated
    public static LocalDateTime castToLocalDateTime(Long value) {
        if (value == null) {
            return null;
        }

        return DateUtil.fromUnixTime(value);
    }

    public static LocalDateTime castToLocalDateTimeMilli(Long value) {
        if (value == null) {
            return null;
        }

        return DateUtil.fromUnixTimeMilli(value);
    }

    @Deprecated
    public static LocalDate castToLocalDate(Long value) {
        LocalDateTime dateTime = castToLocalDateTime(value);
        return dateTime == null ? null : dateTime.toLocalDate();
    }

    public static LocalDate castToLocalDateMilli(Long value) {
        LocalDateTime dateTime = castToLocalDateTimeMilli(value);
        return dateTime == null ? null : dateTime.toLocalDate();
    }

    public static LocalTime castToLocalTime(Integer value) {
        if (value == null) {
            return null;
        }

        return LocalTime.ofSecondOfDay(value);
    }

    public static Long castToUnixTimeMilli(ZonedDateTime time) {
        if (time == null) {
            return null;
        }

        return time.toInstant().toEpochMilli();
    }

    public static Long castToUnixTimeMilli(LocalDateTime time) {
        if (time == null) {
            return null;
        }

        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return time.toInstant(offset).toEpochMilli();
    }

    public static Long castToUnixTimeMilli(LocalDate time) {
        if (time == null) {
            return null;
        }

        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return time.atStartOfDay(offset).toInstant().toEpochMilli();
    }

    public static Integer castToUnixTime(LocalTime time) {
        if (time == null) {
            return null;
        }

        return time.toSecondOfDay();
    }
}
