package com.codingshen.demo.application.service;

import org.springframework.stereotype.Service;

@Service
public class DemoAppServiceImpl {

    public String hello(String name, String msg) {
        return name + msg;
    }

}
