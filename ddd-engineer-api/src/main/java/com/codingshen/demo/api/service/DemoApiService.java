package com.codingshen.demo.api.service;

import com.codingshen.demo.api.common.CommonResult;
import io.swagger.v3.oas.annotations.Operation;

public interface DemoApiService {

    @Operation(summary = "hello")
    public CommonResult<String> hello();

}
