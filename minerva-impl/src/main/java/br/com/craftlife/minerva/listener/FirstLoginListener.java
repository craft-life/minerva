package br.com.craftlife.minerva.listener;

import br.com.craftlife.eureka.server.ServerManager;
import br.com.craftlife.minerva.model.FirstLogin;
import br.com.craftlife.minerva.repository.FirstLoginRepository;
import com.google.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstLoginListener implements Listener {

    @Inject
    private FirstLoginRepository firstLoginRepository;

    @Inject
    private ServerManager serverManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String name = e.getPlayer().getName().toLowerCase();
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        FirstLogin firstLogin = firstLoginRepository.buscaPorId(name);
        if (firstLogin == null) {
            firstLogin = new FirstLogin();
            firstLogin.setUsername(name);
            firstLogin.setTimestamp(timestamp);
            firstLoginRepository.inclui(firstLogin);
        }
        e.setJoinMessage(null);
    }


}
