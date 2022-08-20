package com.baizhi.entity;

import java.io.Serializable;

/**
 * (Emp)实体类
 *
 * @author chenyn
 * @since 2021-05-13 09:06:12
 */
public class Emp implements Serializable {
    private static final long serialVersionUID = 583598508690661937L;

    private Integer id;

    private String name;

    private Integer age;

    private Object salary;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getSalary() {
        return salary;
    }

    public void setSalary(Object salary) {
        this.salary = salary;
    }

}
