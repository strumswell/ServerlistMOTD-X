package cloud.bolte.serverlistmotd.variables;

import java.net.InetAddress;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.util.VaultIntegration;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class MoneyVariable {

	private MoneyVariable() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Get balance from player. Vault and a economy plugin has
	 * to be installed on the server.
	 * @param ip IP from known player
	 * @return balance
	 */
	public static Double getMoney(InetAddress ip) {
		try {
			OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));		
			if (p.hasPlayedBefore()) {
				return VaultIntegration.getEconomy().getBalance(p);
			}else {
				//User is uncached by Spigot
				//p is null
				return 0d;
			}
		} catch (NullPointerException npe) {
			return -1d;
		} catch (NoClassDefFoundError nc) {
			return 0d;
		}
	}
}
