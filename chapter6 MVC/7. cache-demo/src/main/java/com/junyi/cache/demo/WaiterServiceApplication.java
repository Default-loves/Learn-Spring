package com.junyi.cache.demo;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.junyi.cache.demo.controller.CoffeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *  静态资源的访问、在CoffeeController中返回ResponseEntity
 *  访问静态资源：http:localhost:8080/static/spring.png，在application.properties文件中配置了静态资源的路径
 *  P.s：静态资源最好通过CDN(Content Dilivery Network)进行获取
 *
 * @see CoffeeController
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
