package com.junyi.more.complex.controller.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 更加复杂的Controller
 * 能够对参数进行校验，能够使用MultipartFile 处理MULTIPART_FORM_DATA_VALUE类型的Request，编写MoneyFormatter做自定义的类型转换
 */



@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class WaiterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaiterServiceApplication.class, args);

	}

}
