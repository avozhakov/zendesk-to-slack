package com.lineate.slackbot.util;

import com.lineate.slackbot.entity.User;
import com.lineate.slackbot.service.UserService;
import com.lineate.slackbot.service.ZendeskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.zendesk.client.v2.model.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ZendeskObserver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZendeskObserver.class);

    private final ZendeskService zendeskService;
    private final SlackMessenger slackMessenger;
    private final UserService userService;

    private Map<User, List<Request>> requests;
    private final long millisDelay = 10000;

    public ZendeskObserver(ZendeskService zendeskService, SlackMessenger slackMessenger, UserService userService) {
        this.zendeskService = zendeskService;
        this.slackMessenger = slackMessenger;
        this.userService = userService;

        requests = getAllUsersRequests();
    }

    @EventListener(ContextRefreshedEvent.class)
    public void run() {
        new Thread(() -> {
            while (true) {
                userService.getUsers().forEach(user -> {
                    List<Request> requestsForComparing = zendeskService.getUserRequests(user);
                    requests.putIfAbsent(user, requestsForComparing);
                    List<Request> changedRequests = findChangedTickets(user, requestsForComparing);

                    if (!changedRequests.isEmpty()) {
                        changedRequests.forEach(request -> {
                            LOGGER.info("Has changed ticket '{}', the new status is - {}", request.getSubject(), request.getStatus());
                            slackMessenger.sendMessage(createMessage(request));
                        });
                        requests.put(user, requestsForComparing);
                    }
                    try {
                        Thread.sleep(millisDelay);
                    } catch (InterruptedException e) {
                        LOGGER.error("Error trying to sleep cycle.", e);
                    }
                });
            }
        }).start();
    }

    private String createMessage(Request request) {
        return "Status of ticket '" + request.getSubject() + "'" +
                " created " + request.getCreatedAt() +
                " has been changed to '" + request.getStatus().toString().toUpperCase() + "'";
    }

    private List<Request> findChangedTickets(User user, List<Request> newRequests) {
        List<Request> changedTickets = new ArrayList<>();
        for(Request request : newRequests) {
            Optional<Request> optionalRequest = requests.get(user).stream()
                    .filter(t -> t.getId().equals(request.getId()))
                    .findAny();

            if (optionalRequest.isPresent()) {
                Request localRequest = optionalRequest.get();
                if (!request.getStatus().equals(localRequest.getStatus())) {
                    changedTickets.add(request);
                }
            } else {
                changedTickets.add(request);
            }
        }
        return changedTickets;
    }

    private Map<User, List<Request>> getAllUsersRequests() {
        Map<User, List<Request>> usersRequests = new HashMap<>();
        userService.getUsers().forEach(user ->
                usersRequests.put(user, zendeskService.getUserRequests(user))
        );
        return usersRequests;
    }
}
