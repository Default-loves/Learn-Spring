package com.junyi.webclient.demo;

import com.junyi.webclient.demo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootApplication
public class WebclientDemoApplication implements ApplicationRunner {
	@Autowired
	private WebClient webClient;
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebclientDemoApplication.class)
				.web(WebApplicationType.NONE)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl("http://localhost:8080").build();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		CountDownLatch cdl = new CountDownLatch(2);

		webClient.get()
				.uri("/coffee/{id}", 1)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Coffee.class)
				.doOnError(e -> log.info("Error:{}", e))
				.doFinally(r -> cdl.countDown())
				.subscribeOn(Schedulers.single())
				.subscribe(c -> log.info("Coffee id 1:{}", c));

		Mono<Coffee> newCoffee = Mono.just(
				Coffee.builder()
						.name("friday")
						.price(Money.of(CurrencyUnit.of("CNY"), 20.00))
						.build()
		);
		webClient.post()
				.uri("/coffee/")
				.body(newCoffee, Coffee.class)
				.retrieve()
				.bodyToMono(Coffee.class)
				.doOnError(e -> log.info("Error:{}", e))
				.doFinally(f -> cdl.countDown())
				.subscribeOn(Schedulers.single())
				.subscribe(c -> log.info("Coffee create:{}", c));
		cdl.await();

		webClient.get()
				.uri("/coffee/")
				.retrieve()
				.bodyToFlux(Coffee.class)
				.toStream()
				.forEach(c -> log.info("Coffee in list:{}", c));

	}
}
