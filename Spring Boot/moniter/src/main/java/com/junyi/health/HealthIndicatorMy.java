package com.junyi.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @time: 2021/3/8 10:28
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
public class HealthIndicatorMy implements HealthIndicator {

    @Override
    public Health health() {
        try {
            URL url = new
                    URL("http://localhost:8083/health/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int statusCode = conn.getResponseCode();
            if (statusCode >= 200 && statusCode < 300) {
                return Health.up().build();
            } else {
                return Health.down().withDetail("HTTP Status Code", statusCode).build();
            }
        } catch (IOException e) {
            return Health.down(e).build();
        }
    }
}
