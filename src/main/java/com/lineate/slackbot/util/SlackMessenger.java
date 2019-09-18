package com.lineate.slackbot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lineate.slackbot.model.SlackMessage;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SlackMessenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackMessenger.class);

    private final String slackWebHookUrl;

    public SlackMessenger(String slackWebHookUrl) {
        this.slackWebHookUrl = slackWebHookUrl;
    }

    public void sendMessage(String text) {
        new Thread(() -> {
            try {
                SlackMessage slackMessage = SlackMessage.builder()
                        .channel("the-channel-name")
                        .userName("user")
                        .text(text)
                        .iconEmoji(":twice:")
                        .build();

                CloseableHttpClient client = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(slackWebHookUrl);

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(slackMessage);

                StringEntity entity = new StringEntity(json);
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");

                client.execute(httpPost);
                client.close();
            } catch (IOException ex) {
                LOGGER.error("Error trying to send message.", ex);
            }
        }).start();
    }
}
