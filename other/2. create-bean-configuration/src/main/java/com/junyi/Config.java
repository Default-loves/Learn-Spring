package com.junyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {
    public Config() {
      log.info("Config.class");
    }

    @Bean("person")
    public Person person() {
        return new Person("junyi", 10);
    }
}
