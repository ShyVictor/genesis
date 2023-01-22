package dev.shyauroratime.z6.discord.app.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;

public class CommandManager extends ListenerAdapter {

    private final Map<String, BiConsumer<Member, MessageReceivedEvent>> registeredCommands = new HashMap<>();

    public void register(String command, BiConsumer<Member, MessageReceivedEvent> event) {
        registeredCommands.put(command.toLowerCase(), event);
    }

    public BiConsumer<Member, MessageReceivedEvent> getEventConsumer(String command) {
        return registeredCommands.get(command);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String command = event.getMessage().getContentRaw().split(" ")[0].toLowerCase(Locale.ROOT);
        if (command.startsWith("!")) {
            try {
                BiConsumer<Member, MessageReceivedEvent> eventConsumer = this.getEventConsumer(command);
                if (eventConsumer == null) {
                    return;
                }
                eventConsumer.accept(event.getMember(), event);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
