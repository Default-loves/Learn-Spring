package com.junyi.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 基于Redis的HttpSession，实现分布式Session，将Session信息保存在Redis中
 */

@SpringBootApplication
@EnableRedisHttpSession
@RestController
public class SessionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionDemoApplication.class, args);
	}

	@RequestMapping("/hello")
	public String index(HttpSession session, String name) {
		String nameStorge = (String) session.getAttribute("name");
		if (nameStorge == null) {
			session.setAttribute("name", name);
			nameStorge = name;
		}
		return "hello " + nameStorge;
	}
}
