package com.junyi.empty.demo;

import com.junyi.empty.demo.converter.MoneyReadConverter;
import com.junyi.empty.demo.converter.MoneyWriteConverter;
import com.junyi.empty.demo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
@SpringBootApplication
@Slf4j
public class ReactiveMongoDemoApplication implements ApplicationRunner {
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	private CountDownLatch cdl = new CountDownLatch(2);

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoDemoApplication.class, args);
	}

	@Bean
	MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter(), new MoneyWriteConverter()));
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		startFromInsertion(() -> {
			log.info("After insertion");
			updatePrice();
		});
		log.info("Waiting");
		cdl.await();

	}

	private void startFromInsertion(Runnable runnable) {
		mongoTemplate.insertAll(initCoffee())
				.publishOn(Schedulers.elastic())
				.doOnNext(c -> log.info("Coffee :{}", c))
				.doOnComplete(runnable)
				.doFinally(s -> {
					cdl.countDown();
					log.info("Insert finally: {}", s);
				})
				.count()
				.subscribe(r -> log.info("Insert number: {}", r));

	}

	private void updatePrice() {
		mongoTemplate.updateMulti(query(where("price").is(3000L))
		,new Update().inc("price", -500L).currentDate("updateTime"), Coffee.class)
				.doFinally(c -> {
					cdl.countDown();
					log.info("finish update :{}", c);
				})
				.subscribe(r -> log.info("result is : {}", r));

	}

	private List<Coffee> initCoffee() {
		Coffee espresso = Coffee.builder()
				.name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();
		Coffee latte = Coffee.builder()
				.name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();
		return Arrays.asList(espresso, latte);
	}
}
