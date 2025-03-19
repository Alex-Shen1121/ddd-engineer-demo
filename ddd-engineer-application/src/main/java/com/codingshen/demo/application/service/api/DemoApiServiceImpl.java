package com.codingshen.demo.application.service.api;

import com.codingshen.demo.api.common.CommonResult;
import com.codingshen.demo.api.service.DemoApiService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "DemoApi")
public class DemoApiServiceImpl implements DemoApiService {
    @Override
    public CommonResult<String> hello() {
        return CommonResult.success("hello");
    }
}
