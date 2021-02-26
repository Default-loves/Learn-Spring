

针对不同的配置环境，自动化执行初始化代码操作

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

