package com.junyi.ioc.domain;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.awt.print.Book;
import java.util.List;

/**
 * User: JY
 * Date: 2020/7/1 0001
 * Description:
 */
public class User implements BeanNameAware {
    private Long id;

    private String name;

    private String description;

    private transient String beanName;

    private Hobby hobby;

    private House house;

    public String getBeanName() {
        return beanName;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public static User createUser() {
        User user = new User();
        user.setId(-1l);
        return user;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @PostConstruct
    private void init() {
        System.out.println("User Bean [" + this.beanName + "] is initing...");
    }

    @PreDestroy
    public void doDestroy() {
        System.out.println("User Bean [" + this.beanName + "] is destroying...");
    }

    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                ", hobby=" + hobby +
                ", house=" + house +
                '}';
    }
}
