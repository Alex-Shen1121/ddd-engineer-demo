/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2024 All Rights Reserved.
 */
package com.codingshen.demo.infrastructure.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {

    public static LocalDateTime getLocalDateTime(String targetZoneId, Long timeMilli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMilli), ZoneId.of(targetZoneId));
    }

}
