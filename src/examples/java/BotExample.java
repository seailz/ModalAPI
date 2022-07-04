import com.seailz.modalapi.listeners.ModalListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import javax.security.auth.login.LoginException;

public class BotExample {

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("token").build();
        jda.addEventListener(new ModalListener()); // THIS IS CRUCIAL. YOU MUST INCLUDE THIS LINE.

        jda.upsertCommand("modal", "A test modal").queue();
        jda.addEventListener(new ModalCommand());
    }

}
