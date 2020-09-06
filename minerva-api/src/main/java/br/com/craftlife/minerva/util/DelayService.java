package br.com.craftlife.minerva.util;

import br.com.craftlife.eureka.database.memory.IMemoryStorage;
import com.google.inject.Inject;

import java.util.concurrent.TimeUnit;

public class DelayService {

    @Inject
    private IMemoryStorage memoryStorage;

    public boolean checkDelay(String key, String username, TimeUnit timeunit, long value) {
        long now = System.currentTimeMillis();
        String delayKey = "clapi:delay:" + key + ":" + username.toLowerCase();

        if (memoryStorage.containsKey(delayKey)) {
            long time = timeunit.toMillis(value);
            long last = Long.parseLong(memoryStorage.get(delayKey));
            long diff = now - last;
            if (diff < time) {
                return false;
            }
        }
        long seconds = timeunit.toSeconds(value);
        memoryStorage.set(delayKey, Long.toString(now), seconds);
        return true;
    }
    
    public void putInDelay(String key, String username, TimeUnit timeunit, long value) {
        long now = System.currentTimeMillis();
        String delayKey = "clapi:delay:" + key + ":" + username.toLowerCase();

        if (!memoryStorage.containsKey(delayKey)) {
        	long seconds = timeunit.toSeconds(value);
            memoryStorage.set(delayKey, Long.toString(now), seconds);
        }
        
    }
    
    public boolean isInDelay(String key, String username, TimeUnit timeunit, long value) {
        String delayKey = "clapi:delay:" + key + ":" + username.toLowerCase();
        return memoryStorage.containsKey(delayKey);
    }

}
