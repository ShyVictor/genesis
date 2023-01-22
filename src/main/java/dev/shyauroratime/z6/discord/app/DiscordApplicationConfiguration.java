package dev.shyauroratime.z6.discord.app;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DiscordApplicationConfiguration {
    public String BOT_TOKEN;
    public String BOT_NAME;
    public DiscordApplicationConfiguration() {

    }
    public void loadConfiguration(){
        Properties prop = new Properties();
        try (InputStream input = DiscordApplicationConfiguration.class.getResourceAsStream("/config.properties")) {
            prop.load(input);
            this.BOT_TOKEN = prop.getProperty("discord.token");
            this.BOT_NAME = prop.getProperty("discord.name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
