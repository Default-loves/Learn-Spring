package com.junyi.bean.scope;

import com.junyi.ioc.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


/**
 * User: JY
 * Date: 2020/7/8 0008
 * Description: ThreadLocalScope示例
 */
public class ThreadLocalScopeDemo {
    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    private User user(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册TheadLocalScope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });
        applicationContext.refresh();

        singleThread(applicationContext);
        multiThread(applicationContext);

        applicationContext.close();
    }

    private static void multiThread(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                System.out.printf("[ Thread id %s ] user = %s\n", Thread.currentThread().getId(), applicationContext.getBean("user"));
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void singleThread(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            System.out.printf("[ Thread id %s ] user = %s\n", Thread.currentThread().getId(), applicationContext.getBean("user"));
        }

    }
}
