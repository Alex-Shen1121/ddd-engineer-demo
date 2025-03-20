/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2024 All Rights Reserved.
 */
package com.codingshen.demo.infrastructure.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    public static Map<String, List<String>> readHeaderMap(HttpServletRequest request) {
        Enumeration<String> headerList = request.getHeaderNames();
        Map<String, List<String>> headerMap = Maps.newHashMap();
        while (headerList.hasMoreElements()) {
            String headerName = headerList.nextElement();
            List<String> values = Lists.newArrayList();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            while (headerValues.hasMoreElements()) {
                values.add(headerValues.nextElement());
            }

            if (!values.isEmpty()) {
                headerMap.put(headerName, values);
            }
        }
        return headerMap;
    }
}
