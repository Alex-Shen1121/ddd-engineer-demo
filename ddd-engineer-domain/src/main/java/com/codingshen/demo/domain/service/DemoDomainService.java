package com.codingshen.demo.domain.service;

import com.codingshen.demo.domain.configuration.IConfiguration;
import com.codingshen.demo.domain.model.entity.Demo;
import com.codingshen.demo.domain.repositoy.IDemoRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DemoDomainService {
    @Resource
    private IDemoRepository demoRepository;

    @Resource
    private IConfiguration apolloConfiguration;

    public List<Demo> list() {
        String testValue = apolloConfiguration.getTestValue();
        log.info("testValue: {}", testValue);
        return demoRepository.list();
    }
}
