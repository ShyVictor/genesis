package dev.shyauroratime.z6.discord.app.embed;

import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.time.OffsetDateTime;
import java.util.List;

public class GenesisCashflowMessageEmbed extends MessageEmbed {
    public GenesisCashflowMessageEmbed() {
        super(null, "Genesis Service",
                "`",
                EmbedType.UNKNOWN, null,
                0x36393F,
                new Thumbnail("https://media.tenor.com/FXzN2MKhaaEAAAAd/money-take.gif",
                        null, 600, 600),
                null, null, null,
                null, null, null);
    }
}
