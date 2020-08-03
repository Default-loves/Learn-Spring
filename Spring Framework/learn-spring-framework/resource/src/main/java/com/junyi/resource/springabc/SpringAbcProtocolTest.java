package com.junyi.resource.springabc;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * User: JY
 * Date: 2020/7/12 0012
 * Description: springabc协议的测试示例
 */
class SpringAbcProtocolTest {
    // 需要添加以下的启动参数，否则会报错“unknown protocol: springabc”
    // -Djava.protocol.handler.pkgs=com.junyi.resource
    public static void main(String[] args) throws IOException {
        URL url = new URL("springabc:\\META-INF\\default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
