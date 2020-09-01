package cloud.bolte.serverlistmotd.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.PlaceholderAPI;

public class PapiIntegration {
	private static boolean papiIsEnabled = false;
	
	private PapiIntegration() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void setupIntegration() {
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			System.out.println("[ServerlistMOTD] Hooking into PlaceholderAPI.");
			papiIsEnabled = true;
		}
	}
	
	public static String replaceVariables(OfflinePlayer player, String motd) {
		if (papiIsEnabled) {
			return PlaceholderAPI.setPlaceholders(player, motd);
		} 
		return motd;
	}
}
