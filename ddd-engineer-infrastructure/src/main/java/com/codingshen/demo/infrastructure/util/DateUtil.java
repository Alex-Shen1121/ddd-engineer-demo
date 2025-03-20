package com.codingshen.demo.infrastructure.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class DateUtil {

    protected static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected static final DateTimeFormatter DATE_TIME_PATTERN_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm:ss");
    protected static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter TIME_PATTERN = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.format(DATE_TIME_PATTERN);
    }

    public static String format(LocalDate dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.format(DATE_PATTERN);
    }

    public static String format(LocalTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.format(TIME_PATTERN);
    }

    public static LocalDateTime fromUnixTime(Long value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneId.systemDefault());
    }

    public static LocalDateTime fromUnixTime(Long value, ZoneId sourceZoneId, ZoneId targetZoneId) {
        if (Objects.isNull(value)) {
            return null;
        }
        return Instant.ofEpochSecond(value).atZone(sourceZoneId).withZoneSameInstant(targetZoneId).toLocalDateTime();
    }

    public static LocalDateTime fromUnixTimeMilli(Long value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault());
    }

    public static LocalDateTime fromUnixTimeMilli(Long value, ZoneId sourceZoneId, ZoneId targetZoneId) {
        if (Objects.isNull(value)) {
            return null;
        }
        return Instant.ofEpochMilli(value).atZone(sourceZoneId).withZoneSameInstant(targetZoneId).toLocalDateTime();
    }

    public static LocalDateTime fromUnixTimeOrUnixTimeMilli(Long value) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (String.valueOf(value).length() >= 13) {
            return fromUnixTimeMilli(value);
        }
        return fromUnixTime(value);
    }

    public static LocalDateTime fromUnixTimeOrUnixTimeMilli(Long value, ZoneId sourceZoneId, ZoneId targetZoneId) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (String.valueOf(value).length() >= 13) {
            return fromUnixTimeMilli(value, sourceZoneId, targetZoneId);
        }
        return fromUnixTime(value, sourceZoneId, targetZoneId);
    }

    public static LocalDateTime string2DateTime(String dateTime) {
        if (StringUtils.isEmpty(dateTime)) {
            return null;
        }
        if (dateTime.contains("+")) {
            return LocalDateTime.parse(dateTime, DATE_TIME_PATTERN_2);
        }
        return LocalDateTime.parse(dateTime, DATE_TIME_PATTERN);
    }

    public static LocalDateTime string2DateTime(String dateTime, String formatter) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(formatter));
    }

    public static LocalDate string2Date(String dateTime) {
        return LocalDate.parse(dateTime, DATE_PATTERN);
    }

    public static LocalTime string2Time(String dateTime) {
        return LocalTime.parse(dateTime, TIME_PATTERN);
    }

    public static String timestamp2Str(Long timestamp, String formatter) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime localDateTime = fromUnixTime(timestamp);
        return localDateTime.format(DateTimeFormatter.ofPattern(formatter));
    }

    public static Long castToUnixTime(LocalDateTime time) {
        if (time == null) {
            return null;
        }

        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return time.toEpochSecond(offset);
    }

    public static Long castToUnixTimeMs(LocalDateTime time) {
        if (time == null) {
            return null;
        }

        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return time.toInstant(offset).toEpochMilli();
    }

    public static Long castToUnixTimeMs(LocalDateTime time, ZoneId sourceZoneId, ZoneId targetZoneId) {
        if (time == null) {
            return null;
        }

        return time.atZone(sourceZoneId).withZoneSameInstant(targetZoneId).toInstant().toEpochMilli();
    }

    public static Long castToUnixTime(LocalDate time) {
        if (time == null) {
            return null;
        }

        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return time.atStartOfDay(offset).toEpochSecond();
    }

    public static Integer castToUnixTime(LocalTime time) {
        if (time == null) {
            return null;
        }

        return time.toSecondOfDay();
    }

    public static Long secondTimeToMillisecond(Long secondTime) {
        if (secondTime == null) {
            return null;
        }

        return secondTime * 1000;
    }

    public static Long getNow() {
        return System.currentTimeMillis() / 1000;
    }

    public static Long getNowMillis() {
        return System.currentTimeMillis();
    }

    public static long getSecond(long milliSecond) {
        return milliSecond / 1000;
    }

    public static String getLocalDateTimeStandardStr(LocalDateTime localDateTime, ZoneId zoneId) {
        if (localDateTime == null) {
            return "";
        }

        LocalDateTime zoneTime = Objects.isNull(zoneId) ? localDateTime : localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime();
        return zoneTime.format(DATE_TIME_PATTERN);
    }

    public static Long standardStrToUnixTimeMs(String time, ZoneId zoneId) {
        LocalDateTime localDateTime = string2DateTime(time);
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.atZone(zoneId).toEpochSecond() * 1000;
    }

}
