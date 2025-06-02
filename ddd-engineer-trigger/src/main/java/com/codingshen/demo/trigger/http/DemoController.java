package com.codingshen.demo.trigger.http;

import com.codingshen.common.result.CommonResult;
import com.codingshen.demo.application.service.DemoAppServiceImpl;
import com.codingshen.demo.domain.model.entity.Demo;
import com.codingshen.demo.trigger.converter.DemoApiConverter;
import com.codingshen.demo.trigger.model.dto.DemoDTO;
import com.codingshen.demo.trigger.model.request.HelloRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
@Tag(name = "Demo 控制器", description = "Demo 控制器")
@Slf4j
public class DemoController {

    @Resource
    private DemoAppServiceImpl demoAppService;

    @PostMapping("/hello")
    public @Validated CommonResult<List<DemoDTO>> hello(@Validated @RequestBody HelloRequest request) throws InterruptedException {
        log.info("[hello] request:{}", request);

        List<Demo> res = demoAppService.hello(request.getName(), request.getMsg());
        return CommonResult.success(DemoApiConverter.INSTANCE.entities2DTOS(res));
    }
}
