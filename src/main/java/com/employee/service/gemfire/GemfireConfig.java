package com.employee.service.gemfire;

import java.util.Properties;

import org.apache.geode.cache.GemFireCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;

import com.employee.service.model.Employee;

@Configuration
public class GemfireConfig {

	@Bean
	Properties gemfireProperties() {
		Properties gemfireProperties = new Properties();
		gemfireProperties.setProperty("name", "SpringDataGemFireApplication");
		gemfireProperties.setProperty("mcast-port", "0");
		gemfireProperties.setProperty("log-level", "config");
		return gemfireProperties;
	}

	@Bean
	CacheFactoryBean gemfireCache() {
		CacheFactoryBean gemfireCache = new CacheFactoryBean();
		gemfireCache.setClose(true);
		gemfireCache.setProperties(gemfireProperties());
		return gemfireCache;
	}

	@Bean
	LocalRegionFactoryBean<String, Employee> getEmployee(final GemFireCache cache) {
		LocalRegionFactoryBean<String, Employee> employeeRegion = new LocalRegionFactoryBean();
		employeeRegion.setCache(cache);
		employeeRegion.setName("employee");
		return employeeRegion;
	}

}
