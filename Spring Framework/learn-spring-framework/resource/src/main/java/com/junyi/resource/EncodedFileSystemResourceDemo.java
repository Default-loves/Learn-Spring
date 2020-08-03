package com.junyi.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: 带有字符编码的 {@link FileSystemResource} 示例
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) throws IOException {
        String systemPath = System.getProperty("user.dir");
        String projectPath = "/learn-spring-framework/resource";
        String path = systemPath + projectPath + "\\src\\main\\java\\com\\junyi\\resource\\EncodedFileSystemResourceDemo.java";

        File file = new File(path);
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        try(Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
