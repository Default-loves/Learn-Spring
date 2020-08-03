package com.junyi.ioc.domain;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description:
 */
public class House {
    private String name;

    private String description;

    public House() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
