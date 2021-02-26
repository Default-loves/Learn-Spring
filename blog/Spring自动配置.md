### @SpringBootApplication

注解@SpringBootApplication中，包括了几个重要的东西

- @ComponentScan：自动扫描@Component注解相关的类，将类实例化后添加到Spring容器进行统一管理
- @SpringBootConfiguration：未知，和@Configuration注解相关
- @EnableAutoConfiguration：开启自动配置，能够自动化执行默认的配置

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
    
}
```



### @EnableAutoConfiguration

而@EnableAutoConfiguration注解如下所示，包含了两个重要的注解：

- @AutoConfigurationPackage
- @Import({AutoConfigurationImportSelector.class})

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
```



### @Import

@Import注解的作用是将对应的类进行实例化，实现动态创建Bean

Spring处理被Import的类有以下的方式：

- 如果该类实现了 ImportSelector 接口，Spring 容器就会实例化该类，并且调用其 selectImports 方法；
- 如果该类实现了 DeferredImportSelector 接口，则 Spring 容器也会实例化该类并调用其 selectImports方法。DeferredImportSelector 继承了 ImportSelector，区别在于 DeferredImportSelector 实例的 selectImports 方法调用时机晚于 ImportSelector 的实例，要等到 @Configuration 注解中相关的业务全部都处理完了才会调用；
- 如果该类实现了 ImportBeanDefinitionRegistrar 接口，Spring 容器就会实例化该类，并且调用其 registerBeanDefinitions 方法；
- 如果该类没有实现上述三种接口中的任何一个，Spring 容器就会直接实例化该类。



### @ConditionalXXX相关注解

这一类注解有很多，作用是判断，然后决定是否实例化被标注的类

比如@ConditionalOnClass，就是在对应类存在的情况下，才实例化被标注的Bean

```java
@Conditional({OnClassCondition.class})
public @interface ConditionalOnClass {
    Class<?>[] value() default {};

    String[] name() default {};
}
```

原理主要在OnClassCondition这个类中，这个类继承了抽象类SpringBootCondition，SpringBootCondition实现了接口Condition的matches()方法，主要的逻辑就在这里：

```java
public final boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String classOrMethodName = getClassOrMethodName(metadata);

        try {
            // 抽象方法，由子类实现，具体是判断条件是否匹配
            ConditionOutcome outcome = this.getMatchOutcome(context, metadata);
            this.logOutcome(classOrMethodName, outcome);
            this.recordEvaluation(context, classOrMethodName, outcome);
            return outcome.isMatch();
        } catch (NoClassDefFoundError var5) {
            throw new IllegalStateException("Could not evaluate condition on " + classOrMethodName + " due to " + var5.getMessage() + " not found. Make sure your own configuration does not rely on that class. This can also happen if you are @ComponentScanning a springframework package (e.g. if you put a @ComponentScan in the default package by mistake)", var5);
        } catch (RuntimeException var6) {
            throw new IllegalStateException("Error processing condition on " + this.getName(metadata), var6);
        }
    }
```

