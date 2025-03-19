package com.codingshen.demo.trigger.http;

import com.codingshen.demo.api.common.CommonResult;
import com.codingshen.demo.api.model.request.HelloRequest;
import com.codingshen.demo.api.model.response.HelloResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/demo")
@Tag(name = "Demo 控制器", description = "Demo 控制器")
public class DemoController {

    @GetMapping("/hello")
    public @Validated CommonResult<HelloResponse> hello(@Validated @RequestBody HelloRequest request) {
        return CommonResult.success(HelloResponse.builder().msg(request.getName() + "," + request.getMsg()).build());
    }
}
