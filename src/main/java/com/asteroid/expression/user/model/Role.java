package com.asteroid.expression.user.model;

import java.util.Date;

/**
 * @author: YuSai
 * @date: 2020-09-30 15:35
 */
public class Role {

    private Integer id;

    private String role_name;

    private Date create_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

}
