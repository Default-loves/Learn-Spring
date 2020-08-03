### PropertySourcesPropertyResolver#getProperty

```java
org.springframework.core.env.PropertySourcesPropertyResolver#getProperty(java.lang.String, java.lang.Class<T>, boolean);

protected <T> T getProperty(String key, Class<T> targetValueType, boolean resolveNestedPlaceholders) {
    if (this.propertySources != null) {
        for (PropertySource<?> propertySource : this.propertySources) {
            if (logger.isTraceEnabled()) {
                logger.trace("Searching for key '" + key + "' in PropertySource '" +
                             propertySource.getName() + "'");
            }
            // 获取到的值value，类型为object
            Object value = propertySource.getProperty(key);
            if (value != null) {
                if (resolveNestedPlaceholders && value instanceof String) {
                    value = resolveNestedPlaceholders((String) value);
                }
                logKeyFound(key, propertySource, value);
                // 对value进行类型转换
                return convertValueIfNecessary(value, targetValueType);
            }
        }
    }
    if (logger.isTraceEnabled()) {
        logger.trace("Could not find key '" + key + "' in any property source");
    }
    return null;
}
```



### AbstractPropertyResolver#convertValueIfNecessary

```java
org.springframework.core.env.AbstractPropertyResolver#convertValueIfNecessary;

protected <T> T convertValueIfNecessary(Object value, @Nullable Class<T> targetType) {
    if (targetType == null) {
        return (T) value;
    }
    // 底层使用到了ConversionService
    ConversionService conversionServiceToUse = this.conversionService;
    if (conversionServiceToUse == null) {
        // Avoid initialization of shared DefaultConversionService if
        // no standard type conversion is needed in the first place...
        if (ClassUtils.isAssignableValue(targetType, value)) {
            return (T) value;
        }
        // 默认实现
        conversionServiceToUse = DefaultConversionService.getSharedInstance();
    }
    // 使用ConversionService进行类型转换
    return conversionServiceToUse.convert(value, targetType);
}

```

