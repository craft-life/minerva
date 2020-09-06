package br.com.craftlife.minerva.util;

import net.md_5.bungee.api.ChatColor;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class StringUtils {

    private static final NumberFormat FORMAT = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static String moneyFormat(double m) {
        BigDecimal valor = new BigDecimal(m);
        return FORMAT.format(valor);
    }

	public static String formatMessage(String message, Object... args) {
		return String.format(ChatColor.translateAlternateColorCodes('&', message), args);
	}
	
	public static String formatTime(int sec) {
        int hours = sec / 3600;
        int remainder = sec - hours * 3600;
        int mins = remainder / 60;
        remainder -= mins * 60;
        int secs = remainder;
        String time = "";
        if (hours > 0) {
            time = time + hours + " hora" + (hours > 1 ? "s" : "");
        }
        if (!time.equals("")) {
            if ((mins > 0) && (secs > 0)) {
                time = time + ", ";
            } else {
                time = time + " e ";
            }
        }
        if (mins > 0) {
            time = time + mins + " minuto" + (mins > 1 ? "s" : "");
        }
        if ((!time.equals("")) && (secs > 0)) {
            time = time + " e ";
        }
        if (secs > 0) {
            time = time + secs + " segundo" + (secs > 1 ? "s" : "");
        }
        return time.trim();
    }
    
    

}
