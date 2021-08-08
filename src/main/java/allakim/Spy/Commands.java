package allakim.Spy;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Commands extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (event.getAuthor().isBot()) {
            return;
        }

        if (args[0].equalsIgnoreCase(Constants.prefix + "spy")) {

            TextChannel channel = event.getChannel();

            if (!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
                channel.sendMessage("I do not have permissions to join a voice channel!").queue();
                return;
            }

            VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
            if (voiceChannel == null) {
                channel.sendMessage("You are not connected to a voice channel!").queue();
                return;
            }


            List<String> lines;
            try {
                lines = Files.readAllLines(Paths.get(""));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            String location = lines.get(new Random().nextInt(lines.size()));

            ArrayList<Member> members = new ArrayList<>(voiceChannel.getMembers());
            int spyIndex = ThreadLocalRandom.current().nextInt(0, members.size());
            for (int i = 0; i < members.size(); i++) {
                String privateMessage = i == spyIndex ? "You're a spy" : location;
                members.get(i)
                        .getUser()
                        .openPrivateChannel()
                        .queue(privateChannel -> privateChannel.sendMessage(privateMessage).queue());
            }
        }
    }
}
