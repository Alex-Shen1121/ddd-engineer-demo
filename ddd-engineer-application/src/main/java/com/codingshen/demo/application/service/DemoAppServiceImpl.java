package com.codingshen.demo.application.service;

import com.codingshen.demo.api.model.response.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class DemoAppServiceImpl {

    public HelloResponse hello(String name, String msg) {
        return new HelloResponse(name + msg);
    }

}
