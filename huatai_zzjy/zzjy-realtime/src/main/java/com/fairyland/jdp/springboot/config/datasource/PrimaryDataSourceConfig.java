package com.fairyland.jdp.springboot.config.datasource;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = { "com.fairyland.jdp" })
public class PrimaryDataSourceConfig {
//	@Autowired
//	private JpaProperties jpaProperties;
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.jpa")
	public  JpaProperties jpaProperties(){
		return new JpaProperties();
	}

	@Resource(name="dataSource1")
	private DataSource dataSource1;

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource1).properties(jpaProperties().getHibernateProperties(dataSource1))
				.packages("com.fairyland.jdp").persistenceUnit("default")
				.build();
	}

	@Bean
	@Primary
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}
}
