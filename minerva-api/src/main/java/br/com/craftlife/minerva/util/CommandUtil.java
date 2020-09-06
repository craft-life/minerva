package br.com.craftlife.minerva.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;

import com.google.inject.Singleton;

@Singleton
public class CommandUtil {

	private final Map<String, Entry<Method, Object>> commandMap = new HashMap<String, Entry<Method, Object>>();
	private CommandMap map = new SimpleCommandMap(null);

	public void unregisterCommands(String... commands) {
		if (Bukkit.getServer() != null && Bukkit.getServer().getPluginManager() instanceof SimplePluginManager) {
			final SimplePluginManager manager = (SimplePluginManager) Bukkit.getServer().getPluginManager();
			for (String command : commands) {
				try {
					final Field field = SimplePluginManager.class.getDeclaredField("commandMap");
					field.setAccessible(true);
					map = (CommandMap) field.get(manager);
					final Field field2 = SimpleCommandMap.class.getDeclaredField("knownCommands");
					field2.setAccessible(true);
					@SuppressWarnings("unchecked")
					final Map<String, org.bukkit.command.Command> knownCommands = (Map<String, org.bukkit.command.Command>) field2.get(map);
					for (final Entry<String, org.bukkit.command.Command> entry : knownCommands.entrySet()) {
						if (entry.getKey().equals(command)) {
							entry.getValue().unregister(map);
						}
					}
					knownCommands.remove(command);
				} catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException | SecurityException e) {
					e.printStackTrace();
				}
				commandMap.remove(command);
			}
			
		}
		
	}

}

