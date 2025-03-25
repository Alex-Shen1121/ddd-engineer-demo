package com.codingshen.demo.application.service;

import com.codingshen.demo.domain.model.entity.Demo;
import com.codingshen.demo.domain.service.DemoDomainService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoAppServiceImpl {

    @Resource
    private DemoDomainService demoDomainService;

    public List<Demo> hello(String name, String msg) {
        return demoDomainService.list();
    }

}
