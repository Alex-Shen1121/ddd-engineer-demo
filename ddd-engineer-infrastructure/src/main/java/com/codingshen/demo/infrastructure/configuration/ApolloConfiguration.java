package com.codingshen.demo.infrastructure.configuration;

import com.codingshen.demo.domain.configuration.IConfiguration;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.stereotype.Component;

@Component
public class ApolloConfiguration implements IConfiguration {

	private final Config appConfig;

	public ApolloConfiguration() {
		this.appConfig = ConfigService.getAppConfig();
	}

	@Override
	public String getTestValue() {
		return appConfig.getProperty("test.value", "10000");
	}

}
