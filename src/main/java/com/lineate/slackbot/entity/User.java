package com.lineate.slackbot.entity;

import java.util.Objects;

public class User {
    private Integer id;

    private String slackUserId;
    private String name;
    private String login;
    private String password;
    private String responseUrl;

    public User(String slackUserId, String name, String login, String password, String responseUrl) {
        this.slackUserId = slackUserId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.responseUrl = responseUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlackUserId() {
        return slackUserId;
    }

    public void setSlackUserId(String slackUserId) {
        this.slackUserId = slackUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return slackUserId.equals(user.slackUserId) &&
                name.equals(user.name) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                responseUrl.equals(user.responseUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slackUserId, name, login, password, responseUrl);
    }

    @Override
    public String toString() {
        return "User{" +
                "slackUserId='" + slackUserId + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", responseUrl='" + responseUrl + '\'' +
                '}';
    }
}
