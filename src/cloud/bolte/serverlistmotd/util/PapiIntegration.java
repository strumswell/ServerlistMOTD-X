package cloud.bolte.serverlistmotd.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.PlaceholderAPI;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

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
