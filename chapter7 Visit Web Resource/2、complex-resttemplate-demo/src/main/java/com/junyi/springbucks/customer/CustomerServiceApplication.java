package com.junyi.springbucks.customer;

import com.junyi.springbucks.customer.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;


/**
 * Customer Service
 * 访问Web的资源，1、构建RequestEntity，使用exchange方法发送请求；2、使用ParameterizedTypeReference获取批量数据
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
		return builder.build();
//		下面的语句会报错，不能使用
//		return new RestTemplate();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/?name={name}")
				.build("mocha");
		RequestEntity<Void> req = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_XML)
				.build();
		ResponseEntity<String> c = restTemplate.exchange(req, String.class);
		log.info("Request status:{}; Request headers:{}", c.getStatusCode(), c.getHeaders().toString());
		log.info("Coffee:{}", c.getBody());

		String coffeeURI = "http://localhost:8080/coffee/";
		Coffee request = Coffee.builder()
				.name("123")
				.price(Money.of(CurrencyUnit.of("CNY"), 22.00))
				.build();
		Coffee response = restTemplate.postForObject(coffeeURI, request, Coffee.class);
		log.info("Create new coffee:{}", response);

		ParameterizedTypeReference<List<Coffee>> ptr = new ParameterizedTypeReference<List<Coffee>>() {};
		ResponseEntity<List<Coffee>> res = restTemplate.exchange(coffeeURI, HttpMethod.GET, null, ptr);
		res.getBody().forEach(o -> log.info("Coffee:{}", o));
	}
}
