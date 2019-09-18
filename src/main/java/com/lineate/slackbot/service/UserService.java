package com.lineate.slackbot.service;

import com.lineate.slackbot.entity.User;
import com.lineate.slackbot.model.SlackResponse;
import com.lineate.slackbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    @Autowired
    private UserRepository repository;


    public UserService() {
    }

    public SlackResponse login(String userId,
                               String userName,
                               String credentials,
                               String responseUrl) {

        SlackResponse response = new SlackResponse();

        if (credentials.isEmpty()) {
            response.setText("It hasn't got login and password");
            response.setResponseType("in_channel");
            return response;
        }

        String login = credentials.split(" ")[0];
        String password = credentials.split(" ")[1];

        User user = new User(userId, login, password, responseUrl);

        repository.save(user);

        response.setText("User " + user + " has been created");
        response.setResponseType("in_channel");

//        Attachment attachment = new Attachment();
//        attachment.setText("This is the attachment text");
//        attachment.setColor("#0000ff");
//
//        response.setAttachments(Collections.singletonList(attachment));

        return response;
    }

    public SlackResponse logout(String userId, String userName, String credentials, String responseUrl) {
        User user = repository.findBySlackId(userId);
        repository.delete(user);

        SlackResponse response = new SlackResponse();

        response.setText("User " + user + " has been deleted");
        response.setResponseType("in_channel");

        return response;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }
}
