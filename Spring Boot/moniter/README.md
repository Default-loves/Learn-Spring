Spring 中的监控

http://localhost:12345/actuator



### Spring Boot Actuator提供的端点

- 应用配置类： 主要用来获取应用程序中加载的应用配置、环境变量、自动化配置报告等配置类信息，它们与 Spring Boot 应用密切相关。

- 度量指标类： 主要用来获取应用程序运行过程中用于监控的度量指标，比如内存信息、线程池信息、HTTP 请求统计等。

- 操作控制类： 在原生端点中只提供了一个关闭应用的端点，即 /shutdown 端点。



### 扩展端点

#### info

方式一

Spring会自动读取配置文件，比如`yml`等中的`info`的信息，比如下面的配置信息可以在http://localhost:12345/actuator/info中查看到

```yaml
info: 
	app:
				name: example-app
	    encoding: @project.build.sourceEncoding@
	    java:
	      source: @java.version@
	      target: @java.version@
```

方式二

实现接口`InfoContributor`的方法`contribute(Info.Builder builder)`

#### health

实现接口`HealthIndicator`的方法`health()`





### Metric

通过访问http://localhost:12345/actuator/metrics查看Spring提供的监控指标度量，Spring是通过内置的Micrometer库来实现度量指标的收集和分析

Spring默认提供了jvm、操作系统、Tomcat等信息，可以通过访问http://localhost:12345/actuator/metrics/jvm.threads.live查看子项的信息

**添加自定义度量指标**

通过meterRegistry添加自定义的端点

```java
@SpringBootApplication
public class Application implements InitializingBean {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Metrics.addRegistry(new SimpleMeterRegistry());
    }
}


@RestController
@RequestMapping("test")
public class MyController {

    @Autowired
    MeterRegistry meterRegistry;

    @GetMapping("get")
    public String test() {
        meterRegistry.summary("com.junyi.order.number").record(1);
        return "OK";
    }
}
```

访问http://localhost:12345/actuator/metrics/com.junyi.order.number



### 自定义端点

在类使用@Endpoint（id = "mysystem"）注解，然后在方法中使用@ReadOperation，将方法返回的数据显示到自定义端点中，可以通过访问http://localhost:12345/actuator/mysystem获取到自定义的端点信息



使用@ReadOperation的方法可以接收请求中的参数，实现动态查询结果，比如：

```java
@ReadOperation
public Map<String, Object> getMySystemInfoWithParam(@Selector String arg0) 			{
    Map<String, Object> result = new HashMap<>();
    result.put(arg0, "data from DB");
    return result;
}
```

可以访问http://localhost:12345/actuator/mysystem/001，然后显示

```json
{
  "001": "data from DB"
}
```







### Spring Boot Admin 

Spring Boot Admin是一个监控Spring应用程序的具备UI界面的应用程序，它的基本原理就是通过调用Actuator的接口来获取数据，提供更加直观的监控Web界面。

