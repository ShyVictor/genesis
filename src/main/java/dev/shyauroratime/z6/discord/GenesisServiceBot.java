package dev.shyauroratime.z6.discord;


import dev.shyauroratime.z6.discord.app.DiscordApplication;
import dev.shyauroratime.z6.discord.app.DiscordApplicationConfiguration;
import dev.shyauroratime.z6.discord.app.command.CommandManager;
import dev.shyauroratime.z6.discord.app.command.DiscordApplicationCommands;
import dev.shyauroratime.z6.discord.app.event.ButtonListenerAdapter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public final class GenesisServiceBot {
    public final static Logger LOG = LoggerFactory.getLogger(GenesisServiceBot.class);

    public static void main(String[] args){
        startBotApplication();
    }
    final DiscordApplicationCommands discordApplicationCommands = new DiscordApplicationCommands();

    private static void startBotApplication() {
        final DiscordApplicationConfiguration discordConfig = new DiscordApplicationConfiguration();
        discordConfig.loadConfiguration();
        System.out.println("[GENESIS SERVICE V1] Arquivo de configuracoes carregado com sucesso!");
        final DiscordApplication genesisBot = new DiscordApplication(discordConfig);
        System.out.println("[GENESIS SERVICE V1] Aplicacao Discord iniciada e carregada!");
        final DiscordApplicationCommands discordApplicationCommands =
                new DiscordApplicationCommands();
        final CommandManager commandManager = discordApplicationCommands.getCommandManager();
        LOG.info("[GENESIS SERVICE V1] Comandos da Aplicacao Discord carregados!");
        try {
            JDA jda = JDABuilder.createDefault(discordConfig.BOT_TOKEN)
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .setActivity(Activity.playing("Season 2023"))
                    .addEventListeners(commandManager, new ButtonListenerAdapter())
                    .setBulkDeleteSplittingEnabled(true)
                    .build();
            genesisBot.setJDA(jda);
            discordApplicationCommands.registerDiscordApplicationCommands();
        } catch (IllegalArgumentException exception) {
            LOG.warn("[GENESIS SERVICE V1] Erro na autorizacao da aplicacao! Reveja as configuracoes.");
            LOG.warn("[GENESIS SERVICE V1] Mensagem de erro: " + exception.getMessage());
            System.exit(1);
        }
    }

    }

