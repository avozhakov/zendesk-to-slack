package com.lineate.slackbot.endpoint;

import com.lineate.slackbot.entity.User;
import com.lineate.slackbot.model.SlackResponse;
import com.lineate.slackbot.service.UserService;
import com.lineate.slackbot.service.ZendeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zendesk.client.v2.model.Request;
import org.zendesk.client.v2.model.Status;

import java.util.List;

@RestController
public class SlackCommandController {
    @Autowired
    private UserService userService;
    @Autowired
    private ZendeskService zendeskService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse login(@RequestParam("team_id") String teamId,
                               @RequestParam("team_domain") String teamDomain,
                               @RequestParam("channel_id") String channelId,
                               @RequestParam("channel_name") String channelName,
                               @RequestParam("user_id") String userId,
                               @RequestParam("user_name") String userName,
                               @RequestParam("command") String command,
                               @RequestParam("text") String text,
                               @RequestParam("response_url") String responseUrl) {
        return userService.login(userId, channelId, userName, text, responseUrl);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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

    @RequestMapping(value = "/add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestParam("id") String id) {
        Request request = new Request();
        request.setId(new Long(id));
        request.setSubject("new sub");
        request.setStatus(Status.NEW);
        zendeskService.addRequest(request);
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void change(@RequestParam("id") String id) {
        Request request = new Request();

        request.setId(new Long(id));
        request.setSubject("new sub");
        request.setStatus(Status.CLOSED);

        zendeskService.changeStatus(request);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Request> all() {

        return zendeskService.getUserRequests(null);
    }
}
