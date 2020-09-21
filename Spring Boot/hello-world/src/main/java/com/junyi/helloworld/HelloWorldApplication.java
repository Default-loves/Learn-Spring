package com.junyi.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个Spring Boot项目的基础设施：
 * 分页处理
 * 统一的消息回复
 * 多环境配置
 * 日志文件配置Logback
 * 常用工具封装 Hutool
 * Entity到Vo的转换
 * MyBatis Code Generate
 * 全局异常拦截
 * 自定义异常
 */
@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}
