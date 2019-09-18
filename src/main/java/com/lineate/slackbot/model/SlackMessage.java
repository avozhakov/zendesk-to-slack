package com.lineate.slackbot.model;

public class SlackMessage {
    private String channel;
    private String userName;
    private String text;
    private String icon_emoji;

    private SlackMessage() {

    }

    public String getChannel() {
        return channel;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public String getIcon_emoji() {
        return icon_emoji;
    }

    public static Builder builder() {
        return new SlackMessage().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder channel(String channel) {
            SlackMessage.this.channel = channel;
            return this;
        }

        public Builder userName(String userName) {
            SlackMessage.this.userName = userName;
            return this;
        }

        public Builder text(String text) {
            SlackMessage.this.text = text;
            return this;
        }

        public Builder iconEmoji(String iconEmoji) {
            SlackMessage.this.icon_emoji = iconEmoji;
            return this;
        }

        public SlackMessage build() {
            return SlackMessage.this;
        }
    }
}
