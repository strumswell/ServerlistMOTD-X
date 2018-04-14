package cloud.bolte.serverlistmotd.variables;

import java.net.InetAddress;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import cloud.bolte.serverlistmotd.Main;

public class PlayerVariable {
	
	/**
	 * Checks if IP is known in HashMap IP_UUID
	 * @param ip IP from player
	 * @return true/false if known
	 */
	public static boolean isKnownPlayer(InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip)) {
			return true;
		} else return false;
	}
	
	/**
	 * Caution: Doesn't check if ip is recorded in HashMap.
	 * Use isKnownPlayer beforehand! (NPE!)
	 * @param ip IP from known player
	 * @return Name from player
	 */
	public static String getNameFromIP(InetAddress ip) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
		return p.getName();

	}
}
