package com.junyi.springbucks.customer;

import com.junyi.springbucks.customer.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;


/**
 * Customer Service
 * 访问Web的资源，向 Waiter Service 发送get 和 post请求
 */


@SpringBootApplication
@Slf4j
public class CustomerServiceApplication implements ApplicationRunner {
	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(CustomerServiceApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.web(WebApplicationType.NONE)
				.run(args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
		return new RestTemplate();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/{id}")
				.build(1L);
		ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
		log.info("Request status:{}; Request head:{}", c.getStatusCode(), c.getHeaders());
		log.info("Coffee:{}", c.getBody());

		String coffeeURI = "http://localhost:8080/coffee/";
		Coffee request = Coffee.builder()
				.name("friday")
				.price(BigDecimal.valueOf(22.00))
				.build();
		Coffee response = restTemplate.postForObject(coffeeURI, request, Coffee.class);
		log.info("create new coffee:{}", response);

		 String s = restTemplate.getForObject(coffeeURI, String.class);
		 log.info("String:{}", s);
	}
}
