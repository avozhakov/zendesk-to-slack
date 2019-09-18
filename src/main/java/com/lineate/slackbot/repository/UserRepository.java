package com.lineate.slackbot.repository;

import com.lineate.slackbot.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findBySlackId(String slackUserId);
}
