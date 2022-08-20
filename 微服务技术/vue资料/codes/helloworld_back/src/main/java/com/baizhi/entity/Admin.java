package com.baizhi.entity;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author chenyn
 * @since 2021-05-12 17:06:55
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 313336450506333875L;

    private Integer id;

    private String username;

    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
