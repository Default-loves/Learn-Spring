### AbstractApplicationContext#prepareRefresh

```java
org.springframework.context.support.AbstractApplicationContext#prepareRefresh

protected void prepareRefresh() {
    // Switch to active.
    this.startupDate = System.currentTimeMillis();
    // 状态位的设置
    this.closed.set(false);
    this.active.set(true);

    if (logger.isDebugEnabled()) {
        if (logger.isTraceEnabled()) {
            logger.trace("Refreshing " + this);
        }
        else {
            logger.debug("Refreshing " + getDisplayName());
        }
    }

    // Initialize any placeholder property sources in the context environment.
    // 没有具体实现
    initPropertySources();

    // Validate that all properties marked as required are resolvable:
    // 验证Environment中必须的properties，保证其已经配置
    getEnvironment().validateRequiredProperties();

    // Store pre-refresh ApplicationListeners...
    if (this.earlyApplicationListeners == null) {
        this.earlyApplicationListeners = new LinkedHashSet<>(this.applicationListeners);
    }
    else {
        // Reset local application listeners to pre-refresh state.
        this.applicationListeners.clear();
        this.applicationListeners.addAll(this.earlyApplicationListeners);
    }

    // Allow for the collection of early ApplicationEvents,
    // to be published once the multicaster is available...
    this.earlyApplicationEvents = new LinkedHashSet<>();
}

```

