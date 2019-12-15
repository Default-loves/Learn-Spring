package com.junyi.empty.demo;

import com.junyi.empty.demo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用 Reactive 的方式访问 Redis
 */
@Slf4j
@SpringBootApplication
public class ReacitiveRedisApplication implements ApplicationRunner {
	private static final String KEY = "COFFEE_MANU";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ReactiveStringRedisTemplate redisTemplate;

	@Bean
	ReactiveStringRedisTemplate reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
		return new ReactiveStringRedisTemplate(factory);
	}


	public static void main(String[] args) {
		SpringApplication.run(ReacitiveRedisApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ReactiveHashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
		CountDownLatch countDownLatch = new CountDownLatch(1);

		List<Coffee> list = jdbcTemplate.query(
				"select * from t_coffee", (rs, i) ->
					Coffee.builder()
							.id(rs.getLong("id"))
							.name(rs.getString("name"))
							.price(rs.getLong("price"))
							.build()
		);
		Flux.fromIterable(list)
				.publishOn(Schedulers.single())
				.doOnComplete(() ->log.info("list ok"))
				.flatMap(c -> {
					log.info("try to put {}", c.getName());
					return hashOps.put(KEY, c.getName(), c.getPrice().toString());
				})
				.doOnComplete(() -> log.info("set OK"))
				.concatWith(redisTemplate.expire(KEY, Duration.ofMinutes(1)))
				.doOnComplete(() -> log.info("set expire OK"))
				.onErrorResume(e -> {
					log.info("Error: {}", e.getMessage());
					return Mono.just(false);
				})
				.subscribe(b -> log.info("boolean: {}", b),
						e -> log.info("error: {}", e.getMessage()),
						() -> countDownLatch.countDown());
		log.info("Waiting");
		countDownLatch.await();



	}
}
