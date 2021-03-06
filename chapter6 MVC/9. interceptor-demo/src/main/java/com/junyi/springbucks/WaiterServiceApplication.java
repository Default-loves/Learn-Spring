package com.junyi.springbucks;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.junyi.springbucks.controller.PerformanceInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

/**
 *	Spring MVC 拦截器，能够在请求执行前和执行后进行相应的操作
 *  实现WebMvcConfigurer接口，重写addInterceptors方法，添加自己的Interceptor类，这个类需要实现HandlerInterceptor接口
 *  主要用于对运行结果进行log的记录
 */



@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class WaiterServiceApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WaiterServiceApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PerformanceInterceptor())
				.addPathPatterns("/coffee/**")
				.addPathPatterns("/order/**");
	}

	@Bean
	public Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> {
			builder.indentOutput(true);
			builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		};
	}

}
