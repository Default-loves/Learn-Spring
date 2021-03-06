package com.junyi.mybatis.generator.demo;

import com.junyi.mybatis.generator.demo.mapper.CoffeeMapper;
import com.junyi.mybatis.generator.demo.model.Coffee;
import com.junyi.mybatis.generator.demo.model.CoffeeExample;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan("com.junyi.mybatis.generator.demo.mapper")
public class MybatisGeneratorDemoApplication implements ApplicationRunner {
	@Autowired
	private CoffeeMapper coffeeMapper;

	public static void main(String[] args) {
		SpringApplication.run(MybatisGeneratorDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		generateArtifacts();
		playWithArtifacts();
	}



	private void generateArtifacts() throws Exception {
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(
				this.getClass().getResourceAsStream("/generatorConfig.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
	private void playWithArtifacts() {
		Coffee espresso = new Coffee()
				.withName("espresso")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date());
		coffeeMapper.insert(espresso);

		Coffee latte = new Coffee()
				.withName("latte")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date());
		coffeeMapper.insert(latte);

		Coffee c = coffeeMapper.selectByPrimaryKey(1L);
		log.info("Coffee: {}", c);

		CoffeeExample coffeeExample = new CoffeeExample();
		coffeeExample.createCriteria().andNameEqualTo("latte");
		List<Coffee> list = coffeeMapper.selectByExample(coffeeExample);
		list.forEach(o -> log.info("selectd coffee : {}", o));
	}
}
