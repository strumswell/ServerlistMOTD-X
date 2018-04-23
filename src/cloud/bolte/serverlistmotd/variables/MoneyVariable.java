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
	
	private static Main main;
	public MoneyVariable(Main main) {
		MoneyVariable.main = main;
	}
	
	@SuppressWarnings("deprecation")
	public static Double getMoney(InetAddress ip) {
		if (main.getServer().getPluginManager().getPlugin("Vault") != null) {
			return VaultIntegration.economy.getBalance
					(Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName());
		} else return 0d;
	}
}
