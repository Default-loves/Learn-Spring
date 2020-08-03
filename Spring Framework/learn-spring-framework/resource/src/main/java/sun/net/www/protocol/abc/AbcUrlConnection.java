package sun.net.www.protocol.abc;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: JY
 * Date: 2020/7/12 0012
 * Description: abc协议的 {@link URLConnection} 实现
 */
public class AbcUrlConnection extends URLConnection {

    private ClassPathResource resource;
    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    protected AbcUrlConnection(URL url) {
        super(url);
        this.resource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }
    @Override
    public InputStream getInputStream() throws IOException {
        return this.resource.getInputStream();
    }
}
