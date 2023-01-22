package dev.shyauroratime.z6.discord.app;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;

public class DiscordApplication {
    @Getter @Setter
    private JDA JavaDiscordApplicationInstance;
    @Getter
    private final DiscordApplicationConfiguration discordApplicationConfiguration;

    public DiscordApplication(DiscordApplicationConfiguration discordApplicationConfiguration) {
        this.discordApplicationConfiguration = discordApplicationConfiguration;
    }

    public void setJDA(net.dv8tion.jda.api.JDA jda) {
    }
}
