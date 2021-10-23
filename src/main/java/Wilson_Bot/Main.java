package Wilson_Bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends ListenerAdapter {
    public static JDA jda;
    public static void main(String[] args) throws LoginException{
        jda = JDABuilder.createDefault("OTAxMzM2MzMxNzM1NjY2NzA4.YXOY3w.o9RCptGaqJlVKTQWhPW0XDk_wxQ").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("코드 주입 당"));

        jda.addEventListener(new Main());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Random rand = new Random();
        String[] args = event.getMessage().getContentRaw().split(" ");
        String prefix = ";";
        ArrayList<String> list = new ArrayList<String>();

        list.add("삶은 고통에요. 더 이어가봤자 준비된 아픔들이 날 기다릴뿐이죠.");
        list.add("싫어... 이제 그만 죽을래...");
        list.add("나는, 살아남아야 하나요?");
        list.add("아픈건 싫어요.");
        list.add("날 이렇게 엉망으로 만들어놓고, 이제 만족하나요?");
        list.add("아파.. 너무 아파요...");
        if(!event.getAuthor().isBot()) {
            if (args[0].equals(prefix + "윌슨")) {
                int n = rand.nextInt(6);

                if (args.length == 1) event.getChannel().sendMessage(list.get(n)).queue();
            }
        }
    }
}
