package com.lineate.slackbot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "slackid")
    private String slackId;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "responseurl")
    private String responseUrl;

    public User() {
    }

    public User(String slackId, String name, String login, String password, String responseUrl) {
        this.slackId = slackId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlackId() {
        return slackId;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
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
        return slackId.equals(user.slackId) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                responseUrl.equals(user.responseUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slackId, login, password, responseUrl);
    }

    @Override
    public String toString() {
        return "User{" +
                "slackId='" + slackId + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", responseUrl='" + responseUrl + '\'' +
                '}';
    }
}
