package com.spring.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Users implements Serializable
{
    private  Integer id;
    @NotBlank(message = "用户名不能为空")
    @Length(min = 2 ,max = 6 ,message = "长度最小不能小于2")
    private  String name;

    private  Integer age;

    @Override
    public String toString()
    {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }
}
