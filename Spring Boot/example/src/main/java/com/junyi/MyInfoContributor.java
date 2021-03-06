package com.junyi;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

/**
 * @time: 2021/3/3 14:20
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
public class MyInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("create time", new Date());
        builder.withDetail("fruit", Collections.singletonMap("name", "apple"));
    }
}
