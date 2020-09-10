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
        this.saveDefaultConfig();
        easySetup();
        commandUtil.unregisterCommands();
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
