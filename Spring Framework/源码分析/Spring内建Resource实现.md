### ClassPathResource

```java
org.springframework.core.io.ClassPathResource;
@Override
public InputStream getInputStream() throws IOException {
    InputStream is;
    if (this.clazz != null) {	//如果有Class，则通过Class
        is = this.clazz.getResourceAsStream(this.path);
    }
    else if (this.classLoader != null) {	//通过ClassLoader
        is = this.classLoader.getResourceAsStream(this.path);
    }
    else {	//通过系统获得
        is = ClassLoader.getSystemResourceAsStream(this.path);
    }
    if (is == null) {
        throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist");
    }
    return is;
}
    
```

