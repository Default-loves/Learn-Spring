package com.junyi.helloworld.vo;

import lombok.Data;

import java.util.Date;

/**
 * @time: 2020/9/21 17:59
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */

public abstract class BaseVo<T> {

    private Date createDate;
    /** one page have 10 element */
    private Integer pageSize = 10;
    /** current page  */
    private Integer pageNumber = 0;

    /**
     * View Object (VO) will extend this class, and use this function to copy properties, return a entity
     * @return POJO
     */
    abstract T changeToEntity();

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
