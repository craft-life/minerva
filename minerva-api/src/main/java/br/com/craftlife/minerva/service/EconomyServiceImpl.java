package br.com.craftlife.minerva.service;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyServiceImpl implements EconomyService {

    private Economy economy;

    public EconomyServiceImpl() {
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(
                net.milkbowl.vault.economy.Economy.class);
        economy = economyProvider.getProvider();
    }

    @Override
    public double getBalance(Player player) {
        return getBalance(player.getName());
    }

    @SuppressWarnings("deprecation")
    @Override
    public double getBalance(String player) {
        return economy.getBalance(player);
    }

    @Override
    public boolean withdrawPlayer(Player player, double value) {
        return withdrawPlayer(player.getName(), value);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean withdrawPlayer(String player, double value) {
        return economy.withdrawPlayer(player, value).transactionSuccess();
    }

    @Override
    public boolean depositPlayer(Player player, double value) {
        return depositPlayer(player.getName(), value);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean depositPlayer(String player, double value) {
        return economy.depositPlayer(player, value).transactionSuccess();
    }

    @Override
    public boolean has(Player player, double value) {
        return has(player.getName(), value);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean has(String player, double value) {
        return economy.has(player, value);
    }
}
