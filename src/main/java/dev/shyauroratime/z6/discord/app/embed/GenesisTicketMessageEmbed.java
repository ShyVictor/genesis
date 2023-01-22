package dev.shyauroratime.z6.discord.app.embed;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;

public class GenesisTicketMessageEmbed extends MessageEmbed {
    public GenesisTicketMessageEmbed(){
        super(null, "Suporte da Genesis Service",
                "`Olá, bem-vindo ao sistema de suporte da Genesis Service! " +
                        "Estamos aqui para ajudá-lo com qualquer dúvida ou problema que você possa ter.\n\n" +
                        "Se você estiver enfrentando algum problema ou possui alguma dúvida, estaremos felizes em tentar resolvê-lo para você. " +
                        "Use os botões abaixo para poder abrir um ticket de suporte diretamente conosco, fornecendo informações detalhadas sobre seu problema e incluindo qualquer informação relevante." +
                        "\n\nNós faremos o possível para responder rapidamente e resolver seu problema o mais breve possível. " +
                        "Agradecemos pela preferência e estamos ansiosos para ajudá-lo.`", null, null,
                0x36393F, null, null, null,
                null, null,null, null);
    }
    public void generateGenesisTicketMessageEmbed(MessageChannel messageChannel){
        final String path = GenesisTicketMessageEmbed.class.getResource("/genesis.png").getPath();
        final File file = new File(path);
        messageChannel.sendFiles(FileUpload.fromData(file))
                .setEmbeds(this).setActionRow(StringSelectMenu.
                create("suport")
                .addOptions(
                        SelectOption.of("Falar com nosso suporte!", "default-suport")
                                .withDefault(true)
                                .withDescription("Como podemos lhe ajudar?")
                                .withEmoji(Emoji.fromUnicode("❓")),
                        SelectOption.of("Ticket sobre Cashflow", "cashflow-suport") // another way to create a SelectOption
                                .withDescription("Problemas ou dúvidas sobre o serviço de Cashflow.")
                                .withEmoji(Emoji.fromUnicode("\uD83D\uDE80")),
                        SelectOption.of("Ticket sobre Derank", "derank-suport")
                                .withDescription("Problemas ou dúvidas sobre o serviço de Derank")
                                .withEmoji(Emoji.fromUnicode("\uD83D\uDCC9")),
                        SelectOption.of("Ticket sobre Handleveling", "handleveling-suport.")
                                .withDescription("Problemas ou dúvidas sobre o sistema de Handleveling.")
                                .withEmoji(Emoji.fromUnicode("\uD83D\uDCB9")),
                        SelectOption.of("Pagamentos", "payment-suport")
                                .withDescription("Resolução de dúvidas e problemas de pagamentos!")
                                .withEmoji(Emoji.fromUnicode("\uD83D\uDCB7")),
                        SelectOption.of("Denúncia", "report-suport")
                                .withDescription("Fazer denúncia anônima.")
                                .withEmoji(Emoji.fromUnicode("❗")))
                .build()).queue();
    }

}
