package com.junyi.empty.demo;

import com.junyi.empty.demo.converter.MoneyReadConverter;
import com.junyi.empty.demo.converter.MoneyWriteConverter;
import com.junyi.empty.demo.model.Coffee;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.dialect.Dialect;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.function.convert.R2dbcCustomConversions;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 *  在R2DB (H2) 中使用 Reactive
 */
@SpringBootApplication
@Slf4j
public class SimpleR2BDCDemoApplication extends AbstractR2dbcConfiguration implements ApplicationRunner {
	@Autowired
	private DatabaseClient client;

	public static void main(String[] args) {
		SpringApplication.run(SimpleR2BDCDemoApplication.class, args);
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		return new H2ConnectionFactory(
				H2ConnectionConfiguration.builder()
				.inMemory("testdb")
				.username("sa")
				.build());
	}
	@Bean
	public R2dbcCustomConversions r2dbcCustomConversions() {
		Dialect dialect = getDialect(connectionFactory());
		CustomConversions.StoreConversions storeConversions =
				CustomConversions.StoreConversions.of(dialect.getSimpleTypeHolder());
		return new R2dbcCustomConversions(storeConversions,
				Arrays.asList(new MoneyReadConverter(), new MoneyWriteConverter()));
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		CountDownLatch cdl = new CountDownLatch(2);

		client.execute()
				.sql("select * from t_coffee")
				.as(Coffee.class)
				.fetch()
				.first()
				.publishOn(Schedulers.elastic())
				.doFinally(s ->cdl.countDown())
				.subscribe(r -> log.info("Execute result : {}", r));

		client.select()
				.from("t_coffee")
				.orderBy(Sort.by(Sort.Direction.DESC, "id"))
				.page(PageRequest.of(0, 3))
				.as(Coffee.class)
				.fetch()
				.all()
				.doFinally(s -> cdl.countDown())
				.publishOn(Schedulers.elastic())
				.subscribe(r -> log.info("Select result : {}", r));

		log.info("Waiting...");
		cdl.await();

	}
}
