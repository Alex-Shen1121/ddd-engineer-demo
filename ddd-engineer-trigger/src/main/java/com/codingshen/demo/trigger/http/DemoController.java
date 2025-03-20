package com.codingshen.demo.trigger.http;

import com.codingshen.demo.trigger.model.request.HelloRequest;
import com.codingshen.demo.trigger.model.response.HelloResponse;
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
public class DemoController {

    @Resource
    private DemoAppServiceImpl demoAppService;

    @PostMapping("/hello")
    public @Validated CommonResult<HelloResponse> hello(@Validated @RequestBody HelloRequest request) {
        String respMsg = demoAppService.hello(request.getName(), request.getMsg());
        HelloResponse response = new HelloResponse(respMsg);
        return CommonResult.success(response);
    }
}
