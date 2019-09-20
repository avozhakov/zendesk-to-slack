package com.lineate.slackbot.configuration;

import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.SlackClientFactory;
import com.hubspot.slack.client.SlackClientRuntimeConfig;

public class BasicRuntimeConfig {

    public static SlackClient getClient() {
        return SlackClientFactory.defaultFactory().build(get());
    }

    public static SlackClientRuntimeConfig get() {
        return SlackClientRuntimeConfig.builder()
                .setTokenSupplier(() -> "xoxp-176213573904-599439698434-726068410048-1e22975f2540d31f997a86d1d57fbb3a")
                .build();
    }
}
