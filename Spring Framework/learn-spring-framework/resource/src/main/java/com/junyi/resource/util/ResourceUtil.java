package com.junyi.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: Resource 工具类
 */
public interface ResourceUtil {

    public static String getContext(Resource resource) {
        try {
            return getContext(resource, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getContext(Resource resource, String encoding) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        Reader reader = encodedResource.getReader();
        return IOUtils.toString(reader);
    }
}
