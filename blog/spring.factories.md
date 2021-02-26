`spring.factories`文件放置的位置：`META-INF\spring.factories`，作用类似于Java的SPI，实现模块之间的解耦，能够自动创建实现类的实例，并且添加到Spring容器中，由Spring进行管理

spring.factories文件的内容如下：

```java
# Application Listeners
org.springframework.context.ApplicationListener=\
org.springframework.boot.ClearCachesApplicationListener,\
org.springframework.boot.builder.ParentContextCloserApplicationListener,\
org.springframework.boot.context.FileEncodingApplicationListener,\
org.springframework.boot.context.config.AnsiOutputApplicationListener,\
org.springframework.boot.context.config.ConfigFileApplicationListener,\
org.springframework.boot.context.config.DelegatingApplicationListener,\
org.springframework.boot.context.logging.ClasspathLoggingApplicationListener,\
org.springframework.boot.context.logging.LoggingApplicationListener,\
org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener
```

`=`左边是接口的名字，右边是具体的实现类串，实现类之间用“，”隔开



具体对`spring.factories`加载的逻辑为：SpringFactoriesLoader#loadSpringFactories

```java
org.springframework.core.io.support.SpringFactoriesLoader#loadSpringFactories;

public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
    
private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
    MultiValueMap<String, String> result = cache.get(classLoader);
    if (result != null) {
        return result;
    }

    try {
        // 通过classLoader获取全部的META-INF/spring.factories文件
        Enumeration<URL> urls = (classLoader != null ?classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :                                 ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
        result = new LinkedMultiValueMap<>();		// 最终返回的结果
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            UrlResource resource = new UrlResource(url);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            for (Map.Entry<?, ?> entry : properties.entrySet()) {
                String factoryClassName = ((String) entry.getKey()).trim();
                for (String factoryName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
                    result.add(factoryClassName, factoryName.trim());
                }
            }
        }
        cache.put(classLoader, result);
        return result;
    }
    catch (IOException ex) {
        throw new IllegalArgumentException("Unable to load factories from location [" +
                                           FACTORIES_RESOURCE_LOCATION + "]", ex);
    }
}

```

