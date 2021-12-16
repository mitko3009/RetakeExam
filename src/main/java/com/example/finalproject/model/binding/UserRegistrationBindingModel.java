package com.example.finalproject.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UserRegistrationBindingModel {

    @NotNull
    @Length(min = 3)
    private String name;
    @NotNull
    @Length(min = 3)
    private String username;
    @NotNull
    @Length(min = 3)
    private String password;
    @NotNull
    @Length(min = 3)
    private String confirmPassword;
    @Positive
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
