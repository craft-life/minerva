package br.com.craftlife.minerva.service;

import org.bukkit.entity.Player;

public interface EconomyService {

    public double getBalance(Player player);

    public double getBalance(String player);

    public boolean withdrawPlayer(Player player, double value);

    public boolean withdrawPlayer(String player, double value);

    public boolean depositPlayer(Player player, double value);

    public boolean depositPlayer(String player, double value);

    public boolean has(Player player, double value);

    public boolean has(String player, double value);

}
