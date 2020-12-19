package com.junyi.helloworld.vo;

import com.junyi.helloworld.entity.Book;
import com.junyi.helloworld.util.BeanCopierUtil;

import java.io.Serializable;
import java.util.Date;

public class BookVO extends BaseVO<Book> {
    private Integer id;

    private String description;

    private String name;

    private Date createDateTime;

    private Integer authorId;

    private static final long serialVersionUID = 1L;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", description=").append(description);
        sb.append(", name=").append(name);
        sb.append(", createDateTime=").append(createDateTime);
        sb.append(", authorId=").append(authorId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    Book buildEntity() {
        Book target = new Book();
        BeanCopierUtil.copyProperties(this, target);
        return target;
    }
}