package dev.shyauroratime.z6.discord.app.ticket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketDate extends Date {

    private final SimpleDateFormat dateFormat = new
            SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public TicketDate() {
        super();
    }
    @Override
    public String toString() {
        return dateFormat.format(this);
    }
}
