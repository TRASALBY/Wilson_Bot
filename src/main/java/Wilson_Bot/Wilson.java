package Wilson_Bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Wilson extends ListenerAdapter {
    public static JDA jda;
    public static String prefix = ";";

    public static void main(String[] args) throws LoginException {
        String token = "";
        Path path = Paths.get("./token.txt");
        try {
            token = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("코드 주입당"));

        jda.addEventListener(new Commands());
    }

}
