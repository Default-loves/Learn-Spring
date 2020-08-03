BeanFactory创建，对应了AbstractApplicationContext#refresh中的语句：

```java
ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
```



### AbstractApplicationContext#**obtainFreshBeanFactory**

```java
org.springframework.context.support.AbstractApplicationContext#obtainFreshBeanFactory;

//在AbstractApplicationContext类中没有实现，由子类进行具体的实现
protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
    refreshBeanFactory();
    return getBeanFactory();
}
```



### AbstractRefreshableApplicationContext

AbstractApplicationContext类的抽象子类AbstractRefreshableApplicationContext，进行了具体的实现

```java
org.springframework.context.support.AbstractRefreshableApplicationContext#refreshBeanFactory;

protected final void refreshBeanFactory() throws BeansException {
    // 先销毁在重新创建
    if (hasBeanFactory()) {
        destroyBeans();
        closeBeanFactory();
    }
    try {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        beanFactory.setSerializationId(getId());	// beanFactory的id就是ApplicationContext的id
        customizeBeanFactory(beanFactory);	// 设置了是否允许重复定义BeanDefinition和是否允许循环依赖
        loadBeanDefinitions(beanFactory);	// 没有实现，由子类具体实现
        synchronized (this.beanFactoryMonitor) {	// 将局部变量beanFactory关联到类实例变量
            this.beanFactory = beanFactory;
        }
    }
    catch (IOException ex) {
        throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
    }
}
```



loadBeanDefinitions()这个方法在类XmlWebApplicationContext和类AnnotationConfigWebApplicationContext都进行了具体的实现，分别对应了XML加载BeanDefinition和注解加载BeanDefinition

