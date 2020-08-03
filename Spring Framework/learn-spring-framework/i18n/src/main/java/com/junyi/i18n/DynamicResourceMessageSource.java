package com.junyi.i18n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * User: JY
 * Date: 2020/7/12 0012
 * Description: 动态(更新)资源
 * 操作：
 * 1. 通过ResourceLoader获得Resource
 * 2. 定位资源文件，即.properties文件，初始化Properties对象
 * 3. 继承AbstractMessageSource抽象类，实现相应方法
 * 4. 监听资源内容变更，使用Java NIO 2 WatchService技术
 * 5. 使用线程池处理资源更新
 * 6. 重新加载Properties对象
 *
 * 结果：
 * 对msg.properties的更新，经过重新Build Module后，能够实时获取更新后的信息
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {
    private final String resourceFileName = "msg.properties";
    private final String resourcePath = "/META-INF/" + resourceFileName;
    private final String ENCODING = "UTF-8";
    private Resource resource;
    private Properties properties;
    private ResourceLoader resourceLoader;
    private ExecutorService executorService;

    public DynamicResourceMessageSource() {
        this.resource = getResource();
        this.properties = loadProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        onPropertiesChanged();
    }

    private void onPropertiesChanged() {
        if (this.resource.isFile()) {
            try {
                File file = this.resource.getFile();
                Path path = file.toPath();
                //资源所在的文件目录
                Path curDir = path.getParent();
                FileSystem fileSystem = FileSystems.getDefault();
                WatchService watchService = fileSystem.newWatchService();
                //监听文件目录
                curDir.register(watchService, ENTRY_MODIFY);
                //处理资源变化，异步操作
                processChanged(watchService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processChanged(WatchService watchService) {
        this.executorService.submit(() -> {
            while (true) {
                WatchKey watchKey = watchService.take();
                try {
                    if (watchKey.isValid()) {
                        for(WatchEvent event: watchKey.pollEvents()) {
                            // 这个Path为监听的目录
                            Path dirPath = (Path) watchKey.watchable();
                            // 这个PATH为相对路径
                            Path relativePath = (Path) event.context();
                            if (this.resourceFileName.equals(relativePath.getFileName().toString())) {
                                Path path = dirPath.resolve(relativePath);
                                File file = path.toFile();
                                System.out.println(path);
                                Properties properties = loadProperties(new FileReader(file));
                                //更新properties
                                synchronized (this.properties) {
                                    this.properties.clear();
                                    this.properties.putAll(properties);
                                }
                            }
                        }
                    }
                } finally {
                    if (watchKey != null) {
                        watchKey.reset();
                    }
                }
            }
        });
    }

    private Properties loadProperties() {
        EncodedResource encodedResource = new EncodedResource(this.resource, ENCODING);
        try {
            return loadProperties(encodedResource.getReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties loadProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }

    private Resource getResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        return resource;
    }

    private ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader: new DefaultResourceLoader();
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String value = this.properties.getProperty(code);
        if (StringUtils.hasText(value)) {
            return new MessageFormat(value, locale);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource dynamicResourceMessageSource = new DynamicResourceMessageSource();
        for (int i = 0; i < 1000; i++) {
            String s = dynamicResourceMessageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(s);
            Thread.sleep(2000);
        }
    }
}
