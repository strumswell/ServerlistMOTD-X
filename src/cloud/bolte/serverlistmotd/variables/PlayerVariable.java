package cloud.bolte.serverlistmotd.variables;

import java.net.InetAddress;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import cloud.bolte.serverlistmotd.Main;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

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
		if (p.hasPlayedBefore()) {
			return p.getName();
		}else {
			//User is uncached by Spigot
			//p is null
			return "<unknown>";
		}

	}
}
