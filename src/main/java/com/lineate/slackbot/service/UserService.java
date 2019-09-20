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

    @Autowired
    private ZendeskService zendeskService;


    public UserService() {
    }

    public SlackResponse login(String userId,
                               String channelId,
                               String userName,
                               String credentials,
                               String responseUrl) {

        SlackResponse response = new SlackResponse();

        if (credentials.isEmpty() || credentials.split(" ").length < 2) {
            response.setText("It hasn't got login or password");
            response.setResponseType("in_channel");
            return response;
        }

        String login = credentials.split(" ")[0];
        String password = credentials.split(" ")[1];

        User user = new User(userId, channelId, userName, login, password, responseUrl);

        if (!zendeskService.checkUserCredentials(user)) {
            response.setText("Wrong login or password");
            response.setResponseType("in_channel");
            return response;
        }

        if (repository.findBySlackId(userId) == null) {
            repository.save(user);
            response.setText("User " + user.getName() + " has been logged in");
        } else {
            response.setText("User " + user.getName() + " is already logged in");
        }

        response.setResponseType("in_channel");

//        Attachment attachment = new Attachment();
//        attachment.setText("This is the attachment text");
//        attachment.setColor("#0000ff");
//
//        response.setAttachments(Collections.singletonList(attachment));

        return response;
    }

    public SlackResponse logout(String userId, String userName, String responseUrl) {
        User user = repository.findBySlackId(userId);

        SlackResponse response = new SlackResponse();

        if (user != null) {
            repository.delete(user);
            response.setText("User " + user.getName() + " has been logged out");
        } else {
            response.setText("User " + userName + " is not logged in");
        }
        response.setResponseType("in_channel");

        return response;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }
}
