package br.com.craftlife.minerva.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import br.com.craftlife.eureka.server.entity.User;

public class LocationUtil {

    public static boolean isDistanceHigher(Location l1, Location l2, double distance) {
        if (!l1.getWorld().getName().equalsIgnoreCase(l2.getWorld().getName())) {
            return true;
        }
        double diffX = l1.getX() - l2.getX();
        diffX = Math.max(diffX, diffX * -1);
        if (diffX > distance) {
            return true;
        }
        double diffY = l1.getY() - l2.getY();
        diffY = Math.max(diffY, diffY * -1);
        if (diffY > distance) {
            return true;
        }
        double diffZ = l1.getZ() - l2.getZ();
        diffZ = Math.max(diffZ, diffZ * -1);
        return diffZ > distance;
    }
    
    public static Block getTargetBlock(User player, int alcance) {
	    Location local = player.getPlayer().getEyeLocation();
	    Vector direction = local.getDirection().normalize();	 
	    Block bloco = null;
	 
	    for (int i = 0; i <= alcance; i++) {
	        bloco = local.add(direction).getBlock();
	    }
	 
	    return bloco;
	}

    public static String serializeLocation(Location l) {
        if (l == null) {
            return "";
        }
        String s = "";
        s = s + "@w;" + l.getWorld().getName();
        s = s + ":@x;" + l.getBlockX();
        s = s + ":@y;" + l.getBlockY();
        s = s + ":@z;" + l.getBlockZ();
        s = s + ":@p;" + l.getPitch();
        s = s + ":@ya;" + l.getYaw();
        return s;
    }

    public static Location deserializeLocation(String s) {
        try {
            Location l = new Location((World) Bukkit.getWorlds().get(0), 0.0D, 0.0D, 0.0D);
            String[] att = s.split(":");
            String[] arrayOfString1;
            int j = (arrayOfString1 = att).length;
            for (int i = 0; i < j; i++) {
                String attribute = arrayOfString1[i];
                String[] split = attribute.split(";");
                if (split[0].equalsIgnoreCase("@w")) {
                    l.setWorld(Bukkit.getWorld(split[1]));
                }
                if (split[0].equalsIgnoreCase("@x")) {
                    l.setX(Double.parseDouble(split[1]));
                }
                if (split[0].equalsIgnoreCase("@y")) {
                    l.setY(Double.parseDouble(split[1]));
                }
                if (split[0].equalsIgnoreCase("@z")) {
                    l.setZ(Double.parseDouble(split[1]));
                }
                if (split[0].equalsIgnoreCase("@p")) {
                    l.setPitch(Float.parseFloat(split[1]));
                }
                if (split[0].equalsIgnoreCase("@ya")) {
                    l.setYaw(Float.parseFloat(split[1]));
                }
            }
            return l;
        } catch (Exception e) {
        }
        return null;
    }

}
