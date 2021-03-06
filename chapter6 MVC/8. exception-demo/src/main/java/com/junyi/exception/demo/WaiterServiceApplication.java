package com.junyi.exception.demo;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.junyi.exception.demo.controller.GlobalControllerAdvice;
import com.junyi.exception.demo.controller.exception.FormValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring MVC 的异常处理机制
 * FormValidationException用于处理 From表单的异常处理
 * GlobalControllerAdvice中处理ValidationException的异常
 * @see GlobalControllerAdvice
 * @see FormValidationException
 *
 */



@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class WaiterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaiterServiceApplication.class, args);
	}
	@Bean
	public Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> builder.indentOutput(true);
	}

}
