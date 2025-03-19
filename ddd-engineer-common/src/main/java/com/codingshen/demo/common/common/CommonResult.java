package com.codingshen.demo.common.common;

import com.codingshen.demo.common.enums.ResultCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    @Schema(name = "code", description = "编码")
    private int code;

    @Schema(name = "message", description = "返回信息")
    private String message;

    @Schema(name = "data", description = "泛型，返回的具体数据(不同接口data字段类型不同)")
    private T data;

    @Schema(name = "success", description = "请求是否成功")
    private boolean success;

    /**
     * 成功
     */
    public static <T> CommonResult<T> success() {
        CommonResult<T> result = new CommonResult<T>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMsg());
        return result;
    }

    /**
     * 成功
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<T>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> CommonResult<T> error(int code, String message) {
        CommonResult<T> result = new CommonResult<T>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败
     */
    public static <T> CommonResult<T> error(ResultCodeEnum codeEnum) {
        CommonResult<T> result = new CommonResult<T>();
        result.setSuccess(false);
        result.setCode(codeEnum.getCode());
        result.setMessage(codeEnum.getMsg());
        return result;
    }
}
