package com.baizhi.entity;

import java.io.Serializable;

/**
 * (Emp)实体类
 *
 * @author chenyn
 * @since 2021-05-10 09:51:57
 */
public class Emp implements Serializable {
    private static final long serialVersionUID = -64721633514385941L;

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

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
