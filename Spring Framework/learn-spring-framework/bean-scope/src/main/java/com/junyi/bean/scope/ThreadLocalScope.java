package com.junyi.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/8 0008
 * Description: ThreadLocal级别的 Scope
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local-scope";
    private NamedThreadLocal<Map<String, Object>> threadLocal =  new NamedThreadLocal<Map<String, Object>>("thread local scope") {
        @Override
        public Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = threadLocal.get();
        Object object = context.get(name);
        if (object == null) {
            object = objectFactory.getObject();
            context.put(name, object);
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> context = threadLocal.get();
        return context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // TODO
    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String, Object> context = threadLocal.get();
        return context.get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
