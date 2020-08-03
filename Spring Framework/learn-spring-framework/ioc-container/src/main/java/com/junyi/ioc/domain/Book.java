package com.junyi.ioc.domain;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * User: JY
 * Date: 2020/7/10 0010
 * Description: 书
 */
public class Book implements DisposableBean {
    private Integer id;
    private String description;

    public Book() {
    }

    public Book(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    // 销毁的三个方法
    @PreDestroy
    public void preDestroy() {
        this.setDescription("change to 11");
        System.out.println("preDestroy(): " + 11);
    }
    @Override
    public void destroy() throws Exception {
        this.setDescription("change to 12");
        System.out.println("destroy(): " + 12);
    }
    public void doDestroy() {
        this.setDescription("change to 13");
        System.out.println("doDestroy(): " + 13);
    }
    public void finalize() throws Throwable {
        // 该方法不一定会触发，所以是不确定的，要看操作系统
        System.out.println("Method finalize() is running, object is garbage-collecting...");
    }
}
