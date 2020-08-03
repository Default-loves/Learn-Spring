

### AbstractBeanFactory#doGetBean

```java
org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean;

protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType,
			@Nullable final Object[] args, boolean typeCheckOnly) throws BeansException {
    try {
        //获取到的合并后的BeanDefinition
        final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
        checkMergedBeanDefinition(mbd, beanName, args);

        // Guarantee initialization of beans that the current bean depends on.
        String[] dependsOn = mbd.getDependsOn();
        if (dependsOn != null) {
            ...
        }

        // 创建单例对象
        if (mbd.isSingleton()) {
            sharedInstance = getSingleton(beanName, () -> {
                try {
                    //里面有创建Bean的过程
                    return createBean(beanName, mbd, args);
                }
                
            });
            
            bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
        }

        else if (mbd.isPrototype()) {
            ...
        }

        else {
            ...
        }
    }
    catch (BeansException ex) {
        cleanupAfterBeanCreationFailure(beanName);
        throw ex;
    }
}

    ...
    return (T) bean;
        }
}
```

### AbstractAutowireCapableBeanFactory#createBean

```java
org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean;

	protected Object createBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)
			throws BeanCreationException {

		if (logger.isTraceEnabled()) {
			logger.trace("Creating instance of bean '" + beanName + "'");
		}
		RootBeanDefinition mbdToUse = mbd;

		//这儿根据BeanDefinition和beanName创建出了Class
		Class<?> resolvedClass = resolveBeanClass(mbd, beanName);
		if (resolvedClass != null && !mbd.hasBeanClass() && mbd.getBeanClassName() != null) {
			mbdToUse = new RootBeanDefinition(mbd);
			mbdToUse.setBeanClass(resolvedClass);
		}
		...
	}

resolveBeanClass(mbd, beanName)又调用了doResolveBeanClass(mbd, typesToMatch);
```

### AbstractBeanFactory#doResolveBeanClass

```java
org.springframework.beans.factory.support.AbstractBeanFactory#doResolveBeanClass;
	@Nullable
	private Class<?> doResolveBeanClass(RootBeanDefinition mbd, Class<?>... typesToMatch)
			throws ClassNotFoundException {
		// 这儿有两个ClassLoader
		ClassLoader beanClassLoader = getBeanClassLoader();
        // 临时ClassLoader
		ClassLoader dynamicLoader = beanClassLoader;
		boolean freshResolve = false;

		if (!ObjectUtils.isEmpty(typesToMatch)) {
			...
		}

		String className = mbd.getBeanClassName();
		if (className != null) {
			Object evaluated = evaluateBeanDefinitionString(className, mbd);
			if (!className.equals(evaluated)) {
				...
			}
			if (freshResolve) {
				...
				return ClassUtils.forName(className, dynamicLoader);
			}
		}

		// 这儿获取了Class
		return mbd.resolveBeanClass(beanClassLoader);
	}

```

### AbstractBeanDefinition#resolveBeanClass

```java
org.springframework.beans.factory.support.AbstractBeanDefinition#resolveBeanClass;
@Nullable
	public Class<?> resolveBeanClass(@Nullable ClassLoader classLoader) throws ClassNotFoundException {
		String className = getBeanClassName();
		if (className == null) {
			return null;
		}
        // 使用ClassUtils获取到了Class
		Class<?> resolvedClass = ClassUtils.forName(className, classLoader);
		this.beanClass = resolvedClass;
		return resolvedClass;
	}
```

