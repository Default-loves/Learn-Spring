@EnableConfigurationProperties注解的作用主要是，使注解了@ConfigurationProperties的类实例化，也即是创建一个Bean，由Spring管理

当然了也可以不使用@EnableConfigurationProperties，替代的方案是@ConfigurationProperties + @Component，这样的效果是一样的，也是创建一个由Spring管理的Bean

例子如下：

使用方法一：

```java
@ConfigurationProperties(prefix = "com.junyi")
@Data
public class MyConfigurationProperties {
    private Integer id;
    private String name;
}

@SpringBootApplication
@EnableConfigurationProperties(MyConfigurationProperties.class)
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

使用方法二：

```java
@ConfigurationProperties(prefix = "com.junyi")
@Component
@Data
public class MyConfigurationProperties {
    private Integer id;
    private String name;
}

@SpringBootApplication
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

测试使用这个Bean：

```java
@RestController
@Slf4j
public class Controller {

    @Autowired
    MyConfigurationProperties properties;

    @GetMapping("/test")
    public void test() {
        log.info("{}, {}", properties.getId(), properties.getName());
    }
}
```



如果使用报错，即

```java
Description:
Field properties in com.junyi.Controller required a bean of type 'com.junyi.MyConfigurationProperties' that could not be found.

The injection point has the following annotations:
	- @org.springframework.beans.factory.annotation.Autowired(required=true)

Action:
Consider defining a bean of type 'com.junyi.MyConfigurationProperties' in your configuration.
```

那么一般就是没有创建好这个Bean了，Spring容器中没有这个类对象，需要按照上面的使用方法使用注解