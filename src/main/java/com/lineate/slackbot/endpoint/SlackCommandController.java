package com.lineate.slackbot.endpoint;

import com.lineate.slackbot.model.SlackResponse;
import com.lineate.slackbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlackCommandController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse login(@RequestParam("team_id") String teamId,
                               @RequestParam("team_domain") String teamDomain,
                               @RequestParam("channel_id") String channelId,
                               @RequestParam("channel_name") String channelName,
                               @RequestParam("user_id") String userId,
                               @RequestParam("user_name") String userName,
                               @RequestParam("command") String command,
                               @RequestParam("text") String text,
                               @RequestParam("response_url") String responseUrl) {
        return userService.login(userId, userName, text, responseUrl);
    }

    @RequestMapping(value = "/logout",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse logout(@RequestParam("team_id") String teamId,
                                @RequestParam("team_domain") String teamDomain,
                                @RequestParam("channel_id") String channelId,
                                @RequestParam("channel_name") String channelName,
                                @RequestParam("user_id") String userId,
                                @RequestParam("user_name") String userName,
                                @RequestParam("command") String command,
                                @RequestParam("text") String text,
                                @RequestParam("response_url") String responseUrl) {
        return userService.logout(userId, userName, responseUrl);
    }
}
