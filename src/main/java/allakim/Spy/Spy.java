package allakim.Spy;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Spy {

    public static JDA jda;

    //Main method
    public static void main(String[] args) throws LoginException {

        jda = JDABuilder.createDefault(Constants.TOKEN).build();
        jda.getPresence().setActivity(Activity.watching("Kaifuku Jutsushi no Yarinaoshi"));
        jda.addEventListener(new Commands());
    }
}
