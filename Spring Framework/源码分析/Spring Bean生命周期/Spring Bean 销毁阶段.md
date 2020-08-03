Bean的销毁方法有以下几个，上面的优先执行于下面
- @PreDestroy方法
- 实现DisposableBean接口中destroy()方法
- 自定义方法
    - @Bean(destroyMethod = "doDestroy")
    - XML配置，<bean destroy-method="destroy"/>
    - Java API：AbstractBeanDefinition#setDestroyMethodName

销毁前阶段的接口：DestructionAwareBeanPostProcessor#postProcessBeforeDestruction

### DisposableBeanAdapter#destroy

```java
org.springframework.beans.factory.support.DisposableBeanAdapter#destroy;

@Override
public void destroy() {
	if (!CollectionUtils.isEmpty(this.beanPostProcessors)) {
		//@PreDestroy
        //DestructionAwareBeanPostProcessor#postProcessBeforeDestruction
        //两个都会在这儿执行
		for (DestructionAwareBeanPostProcessor processor : this.beanPostProcessors) {
			processor.postProcessBeforeDestruction(this.bean, this.beanName);
		}
	}

	if (this.invokeDisposableBean) {
		if (logger.isTraceEnabled()) {
			logger.trace("Invoking destroy() on bean with name '" + this.beanName + "'");
		}
		try {
			if (System.getSecurityManager() != null) {
				AccessController.doPrivileged((PrivilegedExceptionAction<Object>) () -> {
					((DisposableBean) this.bean).destroy();
					return null;
				}, this.acc);
			}
			else {
				//DisposableBean接口中destroy()方法
				((DisposableBean) this.bean).destroy();
			}
		}
		catch (Throwable ex) {
			String msg = "Invocation of destroy method failed on bean with name '" + this.beanName + "'";
			if (logger.isDebugEnabled()) {
				logger.warn(msg, ex);
			}
			else {
				logger.warn(msg + ": " + ex);
			}
		}
	}

	if (this.destroyMethod != null) {
		//自定义销毁方法
		invokeCustomDestroyMethod(this.destroyMethod);
	}
	else if (this.destroyMethodName != null) {
		Method methodToInvoke = determineDestroyMethod(this.destroyMethodName);
		if (methodToInvoke != null) {
			invokeCustomDestroyMethod(ClassUtils.getInterfaceMethodIfPossible(methodToInvoke));
		}
	}
}

```

