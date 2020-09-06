package br.com.craftlife.minerva.util;

import br.com.craftlife.eureka.injector.resource.InjectMessage;
import br.com.craftlife.eureka.resource.messages.Message;
import br.com.craftlife.eureka.task.Scheduler;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import lombok.Getter;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("chataction")
public class FancyUtil extends BaseCommand {

    @Inject
	Scheduler scheduler;

    @Getter
	private HashMap<UUID, Runnable> actions = new HashMap<>();

    public String createTempCommand(Runnable r, TimeUnit tu, Integer expires) {
        final UUID uuid = UUID.randomUUID();
        actions.put(uuid, r);
        if (expires > 0 && tu != null) {
        	scheduler.schedule(() -> {
        		actions.remove(uuid);
        	}, expires, tu);
        }
        return "/chataction " + uuid.toString();
    }

    public  String createTempCommand(Runnable r) {
        return createTempCommand(r, null, 0);
    }
    
    @InjectMessage("messages.actionnull")
    private Message actionNullMessage;

    @Default
    @Description("realiza ação programada")
    public void chataction(Player player, String uuids) {
        UUID uuid = null;
        try {
            uuid = UUID.fromString(uuids);
        } catch (Exception ex) {
            return;
        }
        if (!actions.containsKey(uuid)) {
            player.sendMessage(actionNullMessage.colored().getSource());
            return;
        }
        Runnable r = actions.remove(uuid);
        r.run();
    }
    
    

}
