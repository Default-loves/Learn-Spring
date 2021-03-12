package com.junyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 创建 Bean RestTemplate
 * @time: 2021/3/11 11:07
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
public class InitBean {

    @Bean
    public RestTemplate restTemplate() {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectionRequestTimeout(3000);
//        factory.setReadTimeout(3000);
//        factory.setConnectTimeout(3000);

//        RestTemplate restTemplate = new RestTemplate(factory);
//        // 设置自定义的异常处理策略
//        restTemplate.setErrorHandler(new ResponseErrorHandler() {
//            @Override
//            public boolean hasError(ClientHttpResponse response) throws IOException {
//                return true;
//            }
//
//            @Override
//            public void handleError(ClientHttpResponse response) throws IOException {
//                log.error("has error: {}", response.getStatusText());
//            }
//        });
//        return new RestTemplate(factory);
        return new RestTemplate();
    }
}
