package br.com.craftlife.minerva.service;


import com.google.inject.Singleton;
import lombok.Getter;
import lombok.Setter;


@Singleton
public class HookService {

    @Getter
    private boolean initialized = false;

    @Setter
    private EconomyService economy = null;

    public void initialize() {
        if (initialized) {
            return;
        }

        initialized = true;
        try {
            Class.forName("net.milkbowl.vault.economy.Economy");
            economy = new EconomyServiceImpl();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

    }

}
