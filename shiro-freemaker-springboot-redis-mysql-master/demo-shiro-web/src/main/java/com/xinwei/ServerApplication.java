package com.xinwei;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.xinwei"})
@EnableJpaRepositories("com.xinwei.dao")
public class ServerApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void run(String... args) {
		log.info("初始化系统...");

	}


	 

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ServerApplication.class, args);
	}
}

