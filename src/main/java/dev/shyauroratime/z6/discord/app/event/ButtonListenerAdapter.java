package dev.shyauroratime.z6.discord.app.event;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.channel.unions.ChannelUnion;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class ButtonListenerAdapter extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equalsIgnoreCase("modal")){
            final TextInput service = TextInput.create("service", "Qual o serviço relacionado?", TextInputStyle.SHORT)
                    .setMinLength(10)
                    .setMaxLength(100)
                    .build();

            TextInput body = TextInput.create("body", "Explique seu problema ou dúvida.", TextInputStyle.PARAGRAPH)
                    .setMinLength(0)
                    .setMaxLength(1000)
                    .setValue("teste")
                    .build();

            Modal modal = Modal.create("ticket", "Suporte Genesis Services")
                    .addActionRows(ActionRow.of(service), ActionRow.of(body))
                    .build();

            event.replyModal(modal).queue();
            event.deferReply(true).queue();

        }
    }
    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (event.getComponentId().equals("suport")) {
            final String test = event.getSelectedOptions().get(0).getLabel();
            final TextInput service = TextInput.create("service", "Qual assunto o problema se encaixa?", TextInputStyle.SHORT)
                    .setValue(test)
                    .setMinLength(10)
                    .setMaxLength(100)
                    .build();
            TextInput body = TextInput.create("body", "Explique seu problema ou dúvida.", TextInputStyle.PARAGRAPH)
                    .setMinLength(0)
                    .setMaxLength(1000)
                    .setValue("teste")
                    .build();
            Modal modal = Modal.create("ticket", "Suporte Genesis Services")
                    .addActionRows(ActionRow.of(service), ActionRow.of(body))
                    .build();
            event.replyModal(modal).queue();
        }

        }
    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals("suporte")) {
            String subject = event.getValue("service").getAsString();
            String body = event.getValue("body").getAsString();
            //createSupportTicket(subject, body);
            String id = event.getValue("service").toString().replace(" ", "-");

        }
    }
    private void createSupportTicket(Guild guild, String subject, String body, String id){
        ChannelAction channelAction = guild.createTextChannel(id);
        channelAction.setParent(guild.getCategoryById("987762652686209055"));
        channelAction.setTopic("Ticket de atendimento");


    }
}






