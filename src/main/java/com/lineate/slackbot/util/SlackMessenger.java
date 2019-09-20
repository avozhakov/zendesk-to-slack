package com.lineate.slackbot.util;

import com.hubspot.algebra.Result;
import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.methods.params.chat.ChatPostMessageParams;
import com.hubspot.slack.client.models.response.SlackError;
import com.hubspot.slack.client.models.response.chat.ChatPostMessageResponse;
import com.lineate.slackbot.configuration.BasicRuntimeConfig;
import com.lineate.slackbot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlackMessenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackMessenger.class);

    public SlackMessenger() {
    }

    public void sendMessage(User user, String text) {
        new Thread(() -> {
            SlackClient client = BasicRuntimeConfig.getClient();

            Result<ChatPostMessageResponse, SlackError> postResult = client.postMessage(
                    ChatPostMessageParams.builder()
                            .setText(text)
                            .setChannelId(user.getChannelId())
                            .build()
            ).join();

            ChatPostMessageResponse response = postResult.unwrapOrElseThrow();
            if (!response.isOk()) {
                LOGGER.error("Error to send message to slack.");
            }

        }).start();
    }
}
