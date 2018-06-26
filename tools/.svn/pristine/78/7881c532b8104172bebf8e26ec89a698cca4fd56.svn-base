package com.tools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages ={"com.tools"})
@EnableDiscoveryClient
@MapperScan("com.tools.project.mapper")
@ServletComponentScan(basePackages = {"com.tools"})
public class InitApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitApplication.class, args);
	}
}
