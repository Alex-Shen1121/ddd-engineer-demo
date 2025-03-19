package com.codingshen.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(0, "success"),
    SERVER_ERROR(500, "server error"),
    REMOTE_INVOKE_ERROR(501, "remote invoke error"),
    AUTH_FAILED(405, "auth failed")
    ;

    private static final Map<Integer, ResultCodeEnum> ENUM_MAP = Arrays.stream(values()).collect(Collectors.toMap(ResultCodeEnum::getCode, Function.identity()));

    private final int code;
    private final String msg;

    public static ResultCodeEnum find(int code) {
        return ENUM_MAP.get(code);
    }
}
