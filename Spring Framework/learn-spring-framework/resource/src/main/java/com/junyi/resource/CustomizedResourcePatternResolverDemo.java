package com.junyi.resource;

import com.junyi.resource.util.ResourceUtil;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: 读取当前package下所有.java的文件
 */
public class CustomizedResourcePatternResolverDemo {
    public static void main(String[] args) throws IOException {
        String systemPath = "/" + System.getProperty("user.dir");
        String projectPath = "/learn-spring-framework/resource";
        String path = systemPath + projectPath + "\\src\\main\\java\\com\\junyi\\resource\\";
        String locationPattern = path + "*.java";

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

//        resolver.setPathMatcher(new MyPathMatcher()); //有Bug

        Resource[] resources = resolver.getResources(locationPattern);
        Stream.of(resources).map(ResourceUtil::getContext).forEach(System.out::println);
    }

    private static class MyPathMatcher implements PathMatcher {
        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
