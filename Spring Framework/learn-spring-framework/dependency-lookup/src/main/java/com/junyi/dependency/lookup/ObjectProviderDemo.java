package com.junyi.dependency.lookup;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: ObjectProvider用于延迟Bean依赖查找
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAviable(applicationContext);
        lookupByStreamOps(applicationContext);

        applicationContext.close();
    }

    /**
     * 通过stream方式，可以查找多个实例
     * @param applicationContext
     */
    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<com.junyi.dependency.domain.Book> beanProvider = applicationContext.getBeanProvider(com.junyi.dependency.domain.Book.class);
        beanProvider.stream().forEach(System.out::println);
    }

    /**
     * 当applicationContext没有相应的Bean(User)时，可以通过ObjectProvider#getIfAvailable返回一个Bean
     * @param applicationContext
     */
    private static void lookupIfAviable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> provider = applicationContext.getBeanProvider(User.class);
        User user = provider.getIfAvailable(User::createUser);
        System.out.println(user);
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<com.junyi.dependency.domain.Book> provider = applicationContext.getBeanProvider(com.junyi.dependency.domain.Book.class);
        System.out.println(provider.getObject());
    }

    @Bean
    @Primary
    public com.junyi.dependency.domain.Book book() {
        com.junyi.dependency.domain.Book book = new com.junyi.dependency.domain.Book();
        book.setId(1);
        book.setDescription("music");
        return book;
    }
    @Bean
    public com.junyi.dependency.domain.Book bookGame() {
        com.junyi.dependency.domain.Book book = new com.junyi.dependency.domain.Book();
        book.setId(1);
        book.setDescription("game");
        return book;
    }

}
