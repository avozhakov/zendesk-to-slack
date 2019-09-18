package com.lineate.slackbot.service;

import com.lineate.slackbot.entity.User;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Request;

import java.util.ArrayList;
import java.util.List;

public class ZendeskService {
    private final String url;

    public ZendeskService(String url) {
        this.url = url;
    }

    public List<Request> getUserRequests(User user) {
        Zendesk zendesk = new Zendesk.Builder(url)
                .setUsername(user.getLogin())
                .setPassword(user.getToken())
                // or .setToken("...")
                .build();

        List<Request> requests = new ArrayList<>();
        zendesk.getRequests().forEach(requests::add);
        return requests;
    }
}
