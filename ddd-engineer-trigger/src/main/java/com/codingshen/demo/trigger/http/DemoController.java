package com.codingshen.demo.trigger.http;

import com.codingshen.demo.api.model.request.HelloRequest;
import com.codingshen.demo.api.model.response.HelloResponse;
import com.codingshen.demo.api.service.DemoApiService;
import com.codingshen.demo.application.service.DemoAppServiceImpl;
import com.codingshen.demo.common.common.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Tag(name = "Demo 控制器", description = "Demo 控制器")
public class DemoController implements DemoApiService {

    @Resource
    private DemoAppServiceImpl demoAppService;

    @PostMapping("/hello")
    public @Validated CommonResult<HelloResponse> hello(@Validated @RequestBody HelloRequest request) {
        return CommonResult.success(demoAppService.hello(request.getName(), request.getMsg()));
    }
}
