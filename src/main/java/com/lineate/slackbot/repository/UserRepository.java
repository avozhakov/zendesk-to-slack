package com.lineate.slackbot.repository;

import com.lineate.slackbot.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {
//        users.add(
//                new User(
//                        "id",
//                        "avozhakov",
//                        "avozhakov@lineate.com",
//                        "123qwe123Z.",
//                        "res"
//                )
//        );
    }

    public List<User> getUsers() {
        return users.stream().map(u ->
                new User(u.getSlackUserId(), u.getName(), u.getLogin(), u.getPassword(), u.getResponseUrl())
        ).collect(Collectors.toList());
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public User deleteUser(User user) {
        users.remove(user);
        return user;
    }

    public User getUserById(String userId) {
        Optional<User> optionalUser = users.stream().filter(user -> user.getSlackUserId().equals(userId)).findAny();
        if (optionalUser.isPresent()) {
            User deletedUser = optionalUser.get();
            users.remove(deletedUser);
            return deletedUser;
        }
        return null;
    }
}
