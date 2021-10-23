package Wilson_Bot;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.ChannelManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import net.dv8tion.jda.api.requests.restaction.PermissionOverrideAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

public class Main extends ListenerAdapter {
    Random rand = new Random();
    public static JDA jda;
    public static void main(String[] args) throws LoginException {
        String token = "";
        Path path = Paths.get("./token.txt");
        try{
            token = Files.readString(path);
        }catch (IOException e){
            e.printStackTrace();
        }

        jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("코드 주입 당"));

        jda.addEventListener(new Main());
    }



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        TextChannel tc = event.getTextChannel();
        Message msg = event.getMessage();
        ArrayList<String> list = new ArrayList<String>();

        list.add("삶은 고통에요. 더 이어가봤자 준비된 아픔들이 날 기다릴뿐이죠.");
        list.add("싫어... 이제 그만 죽을래...");
        list.add("나는, 살아남아야 하나요?");
        list.add("아픈건 싫어요.");
        list.add("날 이렇게 엉망으로 만들어놓고, 이제 만족하나요?");
        list.add("아파.. 너무 아파요...");
        String prefix = ";";

        if (user.isBot()) return;                   //봇이 명령어 입력시 리턴
        if(msg.getContentRaw().charAt(0) == ';'){
            String[] args = msg.getContentRaw().substring(1).split(" ");
            if(args.length <= 0) return;
            if(args[0].equalsIgnoreCase("윌슨")){     //명령어 하나만 입력했을 때
                int n = rand.nextInt(6);
                tc.sendMessage(list.get(n)).queue();
            }
            if(args[0].equalsIgnoreCase("join")){     //join 명령 입력
                if(!event.getGuild().getSelfMember().hasPermission(tc,Permission.VOICE_CONNECT)){
                    tc.sendMessage("권한없음!").queue();
                    return;
                }
                VoiceChannel vc = event.getMember().getVoiceState().getChannel();
                if(vc==null){
                    tc.sendMessage("음성채널에 먼저 들어가 주세요").queue();
                    return;
                }
                AudioManager audioManager = event.getGuild().getAudioManager();
                if(audioManager.isAttemptingToConnect()){
                    tc.sendMessage("이미 들어가 있어요.").queue();
                    return;
                }
                audioManager.openAudioConnection(vc);
                tc.sendMessage("연결 성공").queue();
            }
            if(args[0].equalsIgnoreCase("leave")){
                VoiceChannel vc = event.getGuild().getSelfMember().getVoiceState().getChannel();
                if(vc == null){
                    tc.sendMessage("음성채널에 연결되어 있지 않아요.").queue();
                    return;
                }
                event.getGuild().getAudioManager().closeAudioConnection();;
                tc.sendMessage("연결 종료").queue();
            }
        }
    }

}
