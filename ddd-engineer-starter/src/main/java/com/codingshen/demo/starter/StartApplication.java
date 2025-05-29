package com.codingshen.demo.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.mybatis.spring.annotation.MapperScan;

@Slf4j
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.codingshen.demo", "com.codingshen.common"})
@MapperScan("com.codingshen.demo.infrastructure.dal.dao")
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
		log.info("ddd engineer demo application start successful!");
	}
}
