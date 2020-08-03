package com.junyi.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: 带有字符编码的 {@link FileSystemResourceLoader} 示例
 * @see FileSystemResourceLoader
 * @see EncodedResource
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String systemPath = "/" + System.getProperty("user.dir");   //注意的是，开头需要有一个“/"
        String projectPath = "/learn-spring-framework/resource";
        String path = systemPath + projectPath + "\\src\\main\\java\\com\\junyi\\resource\\EncodedFileSystemResourceLoaderDemo.java";

        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(path);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        try(Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
