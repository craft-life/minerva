package br.com.craftlife.minerva.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    public static String formatStamp(long timestamp) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    public static long parseTimeBlock(String time) {
        long total = 0;
        try {
            total += getTime(time, "s");
        } catch (Exception exc) {
        }
        try {
            total += getTime(time, "m") * 60;
        } catch (Exception exc) {
        }
        try {
            total += getTime(time, "h") * 60 * 60;
        } catch (Exception exc) {
        }
        try {
            total += getTime(time, "d") * 60 * 60 * 24;
        } catch (Exception exc) {
        }
        return total;
    }

    private static long getTime(String time, String suffix) {
        long total = 0;
        Pattern p = Pattern.compile("(?<tempo>[0-9]+)" + suffix);
        Matcher m = p.matcher(time);
        while (m.find()) {
            try {
                total += Integer.parseInt(m.group("tempo"));
            } catch (Exception exc) {
            }
        }
        return total;
    }

    public static String formatTimeBlock(final long sec) {
        final long days = sec / (60 * 60 * 24);
        long remainder = sec - days * (60 * 60 * 24);
        final long hours = remainder / (60 * 60);
        remainder = remainder - hours * (60 * 60);
        final long mins = remainder / 60;
        final long secs = remainder - mins * 60;
        String tempo = "";
        if (days > 0) {
            tempo += days + " dia" + ((days > 1) ? "s" : "");
            if ((mins > 0 && secs > 0) || (hours > 0 && mins > 0) || (hours > 0 && secs > 0)) {
                tempo += ", ";
            } else if (mins > 0 || secs > 0 || hours > 0) {
                tempo += " e ";
            }
        }
        if (hours > 0) {
            tempo += hours + " hora" + ((hours > 1) ? "s" : "");
            if (mins > 0 && secs > 0) {
                tempo += ", ";
            } else if (mins > 0 || secs > 0) {
                tempo += " e ";
            }
        }
        if (mins > 0) {
            tempo += mins + " minuto" + ((mins > 1) ? "s" : "");
            if (secs > 0) {
                tempo += " e ";
            }
        }
        if (secs > 0) {
            tempo += secs + " segundo" + ((secs > 1) ? "s" : "");
        }
        return tempo.trim();
    }

    public static String formatTimeBlockMinimal(final long sec) {
        final long days = sec / (60 * 60 * 24);
        long remainder = sec - days * (60 * 60 * 24);
        final long hours = remainder / (60 * 60);
        remainder = remainder - hours * (60 * 60);
        final long mins = remainder / 60;
        final long secs = remainder - mins * 60;
        String tempo = "";
        if (days > 0) {
            tempo += days + "d";
        }
        if (hours > 0) {
            tempo += hours + "h";
        }
        if (mins > 0) {
            tempo += mins + "m";
        }
        if (secs > 0) {
            tempo += secs + "s";
        }
        return tempo.trim();
    }

}
