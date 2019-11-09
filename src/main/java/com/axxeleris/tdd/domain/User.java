package com.axxeleris.tdd.domain;

public class User {
    private Long id;
    private String userName;
    private String password;
    private Boolean blocked;

    public User(String userName, String password, Boolean blocked) {
        this.userName = userName;
        this.password = password;
        this.blocked = blocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }
}
