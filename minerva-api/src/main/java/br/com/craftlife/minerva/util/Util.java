package br.com.craftlife.minerva.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.Bukkit;

import br.com.craftlife.eureka.server.entity.User;

public class Util {	
	
	public void tpBungee(User p, String s) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(s);
            p.getPlayer().sendPluginMessage(Bukkit.getPluginManager().getPlugin("CraftLifeAPI"), "BungeeCord", b.toByteArray());
        } catch (Exception e) {
        }
    }

}
