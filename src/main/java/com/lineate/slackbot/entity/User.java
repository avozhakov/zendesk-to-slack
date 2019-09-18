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
    @Column(name = "login")
    private String login;
    @Column(name = "token")
    private String token;
    @Column(name = "responseurl")
    private String responseUrl;

    public User() {
    }

    public User(String slackId, String login, String token, String responseUrl) {
        this.slackId = slackId;
        this.login = login;
        this.token = token;
        this.responseUrl = responseUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
                token.equals(user.token) &&
                responseUrl.equals(user.responseUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slackId, login, token, responseUrl);
    }

    @Override
    public String toString() {
        return "User{" +
                "slackId='" + slackId + '\'' +
                ", login='" + login + '\'' +
                ", token='" + token + '\'' +
                ", responseUrl='" + responseUrl + '\'' +
                '}';
    }
}
