package com.codingshen.demo.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 应用启动类
 * @author shenchenyu
 * @date 2025-03-19 17:00
 **/
@Slf4j
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
//@EnableDubbo(scanBasePackages = "com.codingshen.democom.codingshen.demo")
//@MapperScan("com.codingshen.demo.infrastructure.dal.mapper")
@SpringBootApplication(scanBasePackages = {"com.codingshen.demo"})
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
		log.info("ddd engineer demo application start successful!");
	}
}
