package br.com.craftlife.minerva;

import br.com.craftlife.eureka.module.EurekaModule;
import br.com.craftlife.eureka.server.ServerManager;
import br.com.craftlife.minerva.service.HookService;
import br.com.craftlife.minerva.util.CommandUtil;
import com.google.inject.Inject;
import org.bukkit.Bukkit;

public class MinervaModule extends EurekaModule {


    @Inject
    private ServerManager serverManager;

    @Inject
    private CommandUtil commandUtil;

    @Inject
    private HookService hookService;

    @Override
    public void configure() {
        easySetup();
        commandUtil.unregisterCommands(
                "tell", "a", "gamemode", "clear", "give", "about", "restart", "spigot", "reload", "rl", "msg", "help",
                "?", "ver", "w", "version", "", "w", "tp", "list", "plugins", "pl", "god", "ungod"
        );
    }



    @Override
    public void init() {
        if (!serverManager.getOnlineUsers().isEmpty()) {
            serverManager.shutdown();
            return;
        }
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        hookService.initialize();
    }

}
