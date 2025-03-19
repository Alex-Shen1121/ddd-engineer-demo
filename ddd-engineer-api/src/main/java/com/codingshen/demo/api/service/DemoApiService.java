package com.codingshen.demo.api.service;

import com.codingshen.demo.api.model.request.HelloRequest;
import com.codingshen.demo.api.model.response.HelloResponse;
import com.codingshen.demo.common.common.CommonResult;

public interface DemoApiService {

    CommonResult<HelloResponse> hello(HelloRequest request);

}
