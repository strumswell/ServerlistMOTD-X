package cloud.bolte.serverlistmotd.variables;

import java.net.InetAddress;

import org.bukkit.Bukkit;

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
	
	public static Double getMoney(InetAddress ip) {
		try {
			return VaultIntegration.getEcononomy().getBalance
						(Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)));
		} catch (NullPointerException npe) {
			return -1d;
		} catch (NoClassDefFoundError nc) {
			return 0d;
		}
	}
}
