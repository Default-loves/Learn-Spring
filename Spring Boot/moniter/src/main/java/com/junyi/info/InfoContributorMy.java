package com.junyi.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * 扩展 info 端点
 * @time: 2021/3/8 10:23
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
public class InfoContributorMy implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("now time", LocalDateTime.now());
        builder.withDetail("manu", Collections.singletonMap("level one", "apple"));
    }
}
