package dev.shyauroratime.z6.discord.app.event;

import dev.shyauroratime.z6.discord.app.ticket.Ticket;
import dev.shyauroratime.z6.discord.app.ticket.TicketDate;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.channel.unions.ChannelUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.util.EnumSet;

public class ButtonListenerAdapter extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equalsIgnoreCase("closeticket")){
            event.getChannel().delete().queue();
            event.deferEdit().queue();
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
                    .setValue("Escreva seu problema aqui.")
                    .build();
            Modal modal = Modal.create("ticket", "Suporte Genesis Services")
                    .addActionRows(ActionRow.of(service), ActionRow.of(body))
                    .build();
            event.replyModal(modal).queue();
        }
        if (event.getComponentId().equals("choose-service")) {
            final User user = event.getUser();
            String roleId = null;
           switch (event.getValues().get(0)){
               default:
                   roleId = "941428484687953960";
                   break;
               case "derank-service":
                    roleId = "987028848791146537";
                    break;
               case "leveling-service":
                   roleId = "996410626261930085";
                   break;
               case "cashflow-service":
                   roleId = "921855873612124191";
                   break;
           }
            Role role = event.getGuild().getRoleById(roleId);
            event.getGuild().addRoleToMember(user, role).queue();
        }
        //Seja bem-vindo à Genesis! Seu cargo foi adicionado, ele te dá acesso aos canais relacionados ao serviço que você escolheu, vamos dar uma olhada?
        event.reply("Seja bem-vindo à Genesis! Seu cargo foi adicionado, ele te dá acesso aos canais relacionados ao serviço que você escolheu, vamos dar uma olhada? \nSe precisar de ajuda humana não hesite em chamar nossos administradores!\n\nBoa jornada, te desejo toda boa sorte que um robô pode desejar!").setEphemeral(true).queue();

    }
    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals("ticket")) {
            final String subject = event.getValue("service").getAsString();
            final String body = event.getValue("body").getAsString();
            final String id = event.getUser().getDiscriminator();
            final Ticket ticket = Ticket.builder()
                    .ticketName(subject)
                    .ticketContent(body)
                    .ticketOwner(id.toString()).build();
            event.reply("Seu ticket foi criado com sucesso!").setEphemeral(true).queue();

            createSupportTicket(event.getGuild(), ticket, event.getMember());


        }
    }
    private void createSupportTicket(Guild guild, Ticket ticket, Member member){
        final TicketDate ticketDate = new TicketDate();
        final TextChannel ticketChannel = guild.createTextChannel(ticket.getTicketOwner()).
                        setParent(guild.getCategoryById("987762652686209055"))
                                .setTopic(ticket.getTicketName()+" criado em "+ticketDate.toString())
                .complete();
        ticketChannel.getManager().putPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null).queue();


        final EmbedBuilder ticketMessage = new EmbedBuilder()
                .setImage("https://media.discordapp.net/attachments/1069283897948516382/1069285557026435112/genesis.png")
                .setDescription("Aguarde alguns minutos para ser atendido. Nossa equipe de suporte irá atendê-lo em breve.")
                .addField("Autor do Ticket", "<@"+member.getId()+">", false)
                .addField("Conteúdo do Ticket", ticket.getTicketContent(), false)
                        .setColor(0x36393F);
        ticketChannel.sendMessage("||@everyone||").
                setEmbeds(ticketMessage.build())
                .addActionRow(Button.danger("closeticket", "Fechar ticket").withEmoji(Emoji.fromUnicode("❌")))
                .queue();
    }
}






