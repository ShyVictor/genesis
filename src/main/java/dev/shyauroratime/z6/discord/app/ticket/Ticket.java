package dev.shyauroratime.z6.discord.app.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter @AllArgsConstructor
public class Ticket {
    private String ticketContent;
    private String ticketName;
    private String ticketOwner;



}
