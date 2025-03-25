package com.codingshen.demo.domain.service;

import com.codingshen.demo.domain.model.entity.Demo;
import com.codingshen.demo.domain.repositoy.IDemoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoDomainService {
    @Resource
    private IDemoRepository demoRepository;

    public List<Demo> list() {
        return demoRepository.list();
    }
}
