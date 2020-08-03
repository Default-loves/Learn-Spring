package sun.net.www.protocol.abc;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * User: JY
 * Date: 2020/7/12 0012
 * Description: abc协议的测试示例
 * @see AbcUrlConnection
 * @see Handler
 */
public class AbcProtocolTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("abc:\\META-INF\\default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
