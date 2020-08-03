RootBeanDefinition没有parent，不需要合并，而继承了其他类的子类由于有parent，是一个GenericBeanDefinition，需要和parent BeanDefinition合并

接口：org.springframework.beans.factory.config.ConfigurableBeanFactory#getMergedBeanDefinition

其实现是唯一的：org.springframework.beans.factory.support.AbstractBeanFactory#getMergedBeanDefinition(java.lang.String)

```java
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
    
	@Override
	public BeanDefinition getMergedBeanDefinition(String name) throws BeansException {
		String beanName = transformedBeanName(name);
		// Efficiently check whether bean definition exists in this factory.
		if (!containsBeanDefinition(beanName) && getParentBeanFactory() instanceof ConfigurableBeanFactory) {
			return ((ConfigurableBeanFactory) getParentBeanFactory()).getMergedBeanDefinition(beanName);
		}
		// Resolve merged bean definition locally.
		return getMergedLocalBeanDefinition(beanName);
	}
    
    protected RootBeanDefinition getMergedLocalBeanDefinition(String beanName) throws BeansException {
		// Quick check on the concurrent map first, with minimal locking.只关注当前，不关心父类的
		RootBeanDefinition mbd = this.mergedBeanDefinitions.get(beanName);
		if (mbd != null && !mbd.stale) {
			return mbd;
		}
		return getMergedBeanDefinition(beanName, getBeanDefinition(beanName));
	}
    
    protected RootBeanDefinition getMergedBeanDefinition(String beanName, BeanDefinition bd)
			throws BeanDefinitionStoreException {

		return getMergedBeanDefinition(beanName, bd, null);
	}

}
```

由于下面的方法很长，所以单独列出，承接上面

```java
protected RootBeanDefinition getMergedBeanDefinition(
    String beanName, BeanDefinition bd, @Nullable BeanDefinition containingBd)
    throws BeanDefinitionStoreException {

    synchronized (this.mergedBeanDefinitions) {
        RootBeanDefinition mbd = null;
        RootBeanDefinition previous = null;

        // Check with full lock now in order to enforce the same merged instance.
        if (containingBd == null) {		//说明当前BeanDefinition不是嵌套的
            mbd = this.mergedBeanDefinitions.get(beanName);
        }

        if (mbd == null || mbd.stale) {	//没有被缓存住或者过期了
            previous = mbd;
            if (bd.getParentName() == null) {
                // Use copy of given root bean definition.
                if (bd instanceof RootBeanDefinition) {
                    mbd = ((RootBeanDefinition) bd).cloneBeanDefinition();
                }
                else {
                    mbd = new RootBeanDefinition(bd);
                }
            }
            else {
                // Child bean definition: needs to be merged with parent.
                BeanDefinition pbd;
                try {
                    String parentBeanName = transformedBeanName(bd.getParentName());	//规范化名称
                    if (!beanName.equals(parentBeanName)) {
                        pbd = getMergedBeanDefinition(parentBeanName);
                    }
                    else {
                        BeanFactory parent = getParentBeanFactory();
                        if (parent instanceof ConfigurableBeanFactory) {
                            pbd = ((ConfigurableBeanFactory) parent).getMergedBeanDefinition(parentBeanName);
                        }
                        else {
                            throw new NoSuchBeanDefinitionException(parentBeanName,
                                                                    "Parent name '" + parentBeanName + "' is equal to bean name '" + beanName +
                                                                    "': cannot be resolved without an AbstractBeanFactory parent");
                        }
                    }
                }
                catch (NoSuchBeanDefinitionException ex) {
                    throw new BeanDefinitionStoreException(bd.getResourceDescription(), beanName,
                                                           "Could not resolve parent bean definition '" + bd.getParentName() + "'", ex);
                }
                // Deep copy with overridden values.
                mbd = new RootBeanDefinition(pbd);
                //子类的属性覆盖父类的属性
                mbd.overrideFrom(bd);
            }

            // Set default singleton scope, if not configured before.
            if (!StringUtils.hasLength(mbd.getScope())) {
                mbd.setScope(SCOPE_SINGLETON);
            }

            // A bean contained in a non-singleton bean cannot be a singleton itself.
            // Let's correct this on the fly here, since this might be the result of
            // parent-child merging for the outer bean, in which case the original inner bean
            // definition will not have inherited the merged outer bean's singleton status.
            if (containingBd != null && !containingBd.isSingleton() && mbd.isSingleton()) {
                mbd.setScope(containingBd.getScope());
            }

            // Cache the merged bean definition for the time being
            // (it might still get re-merged later on in order to pick up metadata changes)
            if (containingBd == null && isCacheBeanMetadata()) {
                this.mergedBeanDefinitions.put(beanName, mbd);
            }
        }
        if (previous != null) {
            copyRelevantMergedBeanDefinitionCaches(previous, mbd);
        }
        return mbd;
    }
}

```

函数最后返回的`return mbd`，可以发现返回的结果都是RootBeanDefinition，