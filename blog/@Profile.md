

### @Profile

我们知道Spring 的 profile，主要就是为了能够分离不同环境下的配置文件，比如开发、测试、生产等，通过`spring.profile.active=dev`来指定读取开发的配置文件

除了读取配置文件，也可以控制在不同的环境执行不同的代码，比如在开发环境下才执行以下的操作：

```java
@Profile("dev")
@Configuration
public class DevDataInitConfig {
 
@Bean
  public CommandLineRunner dataInit() { 
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        //执行 Dev 环境的数据初始化
    };  
}
```

或者执行不同环境下的数据初始化操作：

```java
@Configuration
public class DataSourceConfig {
 
    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        //创建 dev 环境下的 DataSource 
    }
 
    @Bean()
    @Profile("prod")
    public DataSource prodDataSource(){
        //创建 prod 环境下的 DataSource 
    }
}
```

