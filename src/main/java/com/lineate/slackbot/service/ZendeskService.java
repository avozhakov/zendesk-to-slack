package com.lineate.slackbot.service;

import com.lineate.slackbot.entity.User;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Request;
import org.zendesk.client.v2.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ZendeskService {
    private final String url;
    private List<Request> requests = new ArrayList<>();

    public void addRequest(Request request) {
        requests.add(request);
        System.out.println("request added");
        System.out.println(requests);
    }

    public void changeStatus(Request request) {
        Optional<Request> any = requests.stream().filter(r -> r.getId().equals(request.getId())).findAny();
        if (any.isPresent()) {
            Request r = any.get();
            r.setStatus(request.getStatus());
            System.out.println("status changed");
            System.out.println(requests);
        }
    }

    public ZendeskService(String url) {
        this.url = url;
    }

    public List<Request> getUserRequests(User user) {
        List<Request> copy = new ArrayList<>();
        requests.forEach(request -> {
            Request newRequest = new Request();

            newRequest.setId(request.getId());
            newRequest.setSubject(request.getSubject());
            newRequest.setStatus(request.getStatus());

            copy.add(newRequest);
        });
        return copy;
    }

    public boolean checkUserCredentials(User user) {
        Zendesk zendesk = new Zendesk.Builder(url)
                .setUsername(user.getLogin())
                .setPassword(user.getPassword())
                .build();

        return zendesk.getCurrentUser().getId() != null;
    }

//    public List<Request> getUserRequests(User user) {
//        Zendesk zendesk = new Zendesk.Builder(url)
//                .setUsername(user.getLogin())
//                .setPassword(user.getPassword())
//                // or .setPassword("...")
//                .build();
//
//
//        List<Request> requests = new ArrayList<>();
//        zendesk.getRequests().forEach(requests::add);
//        return requests;
//    }
}
