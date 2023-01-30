package dev.shyauroratime.z6.discord.app.command;

import dev.shyauroratime.z6.discord.app.embed.GenesisCashflowMessageEmbed;
import dev.shyauroratime.z6.discord.app.embed.GenesisTicketMessageEmbed;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.awt.*;

public class DiscordApplicationCommands {

    @Getter
    private final CommandManager commandManager;
    public DiscordApplicationCommands(){
       this.commandManager = new CommandManager();
    }
    public void registerDiscordApplicationCommands(){
        commandManager.register("!05022002suporte", ((member, event) ->
        {
            final MessageChannel textChannel = event.getChannel();

            final GenesisTicketMessageEmbed genesisTicketMessageEmbed =
                    new GenesisTicketMessageEmbed();
            genesisTicketMessageEmbed.generateGenesisTicketMessageEmbed(event.getChannel());

        }));
        commandManager.register("!05022002service", ((member, event) ->
        {
            event.getChannel().sendMessage("Olá, invocador! Qual serviço deseja realizar?").setActionRow(StringSelectMenu.
                    create("choose-service")
                    .addOptions(SelectOption.of("Escolha como ganhar dinheiro!", "default").withDefault(true).withDescription("Ganhe dólares e euros jogando League of Legends na Genesis service!")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDCB7")),SelectOption.of("Leveling",
                                            "leveling-service") // another way to create a SelectOption
                                    .withDescription("Ganhe dinheiro jogando URF, ARAM e modos especiais!")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDCB9")),
                            SelectOption.of("Derank", "derank-service")
                                    .withDescription("Ganhe dinheiro perdendo partidas ranqueadas!").withEmoji(Emoji.fromUnicode("\uD83D\uDCC9")),
                            SelectOption.of("Cashflow", "cashflow-service")
                                    .withDescription("Ganhe dólares jogando partidas ranqueadas!")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDE80")))
                    .build()).queue();
        }));
    }
}
