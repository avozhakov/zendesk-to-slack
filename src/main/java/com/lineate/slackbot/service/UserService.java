package com.lineate.slackbot.service;

import com.lineate.slackbot.entity.User;
import com.lineate.slackbot.model.SlackResponse;
import com.lineate.slackbot.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository repository;


    public UserService(UserRepository repository) {
        this.repository = repository;
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

        User user = new User(userId, userName, login, password, responseUrl);

        repository.createUser(user);

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
        User user = repository.getUserById(userId);
        repository.deleteUser(user);

        SlackResponse response = new SlackResponse();

        response.setText("User " + user + " has been deleted");
        response.setResponseType("in_channel");

        return response;
    }

    public List<User> getUsers() {
        return repository.getUsers();
    }
}
