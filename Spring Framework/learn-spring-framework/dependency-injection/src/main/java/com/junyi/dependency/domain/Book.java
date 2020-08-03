package com.junyi.dependency.domain;

import java.util.Arrays;
import java.util.List;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description:
 */
public class Book {
    private City[] workingCity;
    private List<City> livingCity;

    public City[] getWorkingCity() {
        return workingCity;
    }

    public void setWorkingCity(City[] workingCity) {
        this.workingCity = workingCity;
    }

    public List<City> getLivingCity() {
        return livingCity;
    }

    public void setLivingCity(List<City> livingCity) {
        this.livingCity = livingCity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "workingCity=" + Arrays.toString(workingCity) +
                ", livingCity=" + livingCity +
                '}';
    }
}
