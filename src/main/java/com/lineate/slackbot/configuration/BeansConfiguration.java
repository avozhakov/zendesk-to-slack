package com.lineate.slackbot.configuration;

import com.lineate.slackbot.service.UserService;
import com.lineate.slackbot.service.ZendeskService;
import com.lineate.slackbot.util.SlackMessenger;
import com.lineate.slackbot.util.ZendeskObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public ZendeskService zendeskService(@Value("${zendesk.url}") String url) {
        return new ZendeskService(url);
    }

    @Bean
    ZendeskObserver zendeskObserver(ZendeskService zendeskService,
                                    SlackMessenger slackMessenger,
                                    UserService userService) {
        return new ZendeskObserver(zendeskService, slackMessenger, userService);
    }

    @Bean
    public SlackMessenger slackMessenger() {
        return new SlackMessenger();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    BasicRuntimeConfig basicRuntimeConfig(@Value("${token.supplier}") String token) {
        return new BasicRuntimeConfig(token);
    }
}
