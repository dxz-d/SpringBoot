package com.diaoxiuze.shiro.poject;

/**
 * @Description 用户
 * @Author 张小黑的猫
 * @data 2019-05-22 19:18
 */
public class User {
    private String username;
    private String password;
    private Boolean available;

    public User(String username, String password, Boolean available) {
        this.username = username;
        this.password = password;
        this.available = available;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}