package com.junyi.validation;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: Spring Bean Validation示例
 * @see MethodValidationPostProcessor
 * @see LocalValidatorFactoryBean
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:\\META-INF\\bean-validation.xml");
        BookProcessor bookProcessor = applicationContext.getBean(BookProcessor.class);
        bookProcessor.process(new Book());

        applicationContext.close();
    }

    @Component
    @Validated
    public static class BookProcessor {
        public void process(@Valid Book book) {
            System.out.println(book);
        }

    }
    private static class Book{
        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
