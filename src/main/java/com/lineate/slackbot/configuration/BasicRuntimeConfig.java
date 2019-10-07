package com.lineate.slackbot.configuration;

import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.SlackClientFactory;
import com.hubspot.slack.client.SlackClientRuntimeConfig;

public class BasicRuntimeConfig {
    private static String TOKEN_SUPPLIER;

    public BasicRuntimeConfig(String token) {
        TOKEN_SUPPLIER = token;
    }

    public static SlackClient getClient() {
        return SlackClientFactory.defaultFactory().build(get());
    }

    private static SlackClientRuntimeConfig get() {
        return SlackClientRuntimeConfig.builder()
                .setTokenSupplier(() -> TOKEN_SUPPLIER)
                .build();
    }
}
