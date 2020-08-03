package sun.net.www.protocol.abc;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * User: JY
 * Date: 2020/7/12 0012
 * Description: abc协议的 Handler
 */
public class Handler extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new AbcUrlConnection(u);
    }
}
