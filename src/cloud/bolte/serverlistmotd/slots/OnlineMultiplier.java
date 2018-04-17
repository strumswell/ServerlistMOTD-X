package cloud.bolte.serverlistmotd.slots;

import org.bukkit.Bukkit;

import com.comphenix.protocol.wrappers.WrappedServerPing;

import cloud.bolte.serverlistmotd.SpigotConfig;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class OnlineMultiplier {
	
	/**
	 * Set slots and online players according to OnlineMultiplier settings in config.
	 * @param ping Ping object from ProtocolLib
	 */
	public static void activateOnlineMultiplier(WrappedServerPing ping) {
		
		int multipliedOnlinePlayer = (int) Math.round(Bukkit.getOnlinePlayers().size() * SpigotConfig.getOnlineMultiplier());
		
		if (multipliedOnlinePlayer >= SpigotConfig.getOnlineMultiplierMinSlots()) {
			ping.setPlayersMaximum(multipliedOnlinePlayer + SpigotConfig.getOnlineMultiplierAddSlots());
			ping.setPlayersOnline(multipliedOnlinePlayer);
		} else {
			ping.setPlayersOnline(multipliedOnlinePlayer);
			ping.setPlayersMaximum(SpigotConfig.getOnlineMultiplierMinSlots());
		}

		if (multipliedOnlinePlayer >= SpigotConfig.getOnlineMultiplierMaxSlots()) {
			ping.setPlayersMaximum(SpigotConfig.getOnlineMultiplierMaxSlots());
			ping.setPlayersOnline(SpigotConfig.getOnlineMultiplierMaxSlots());
		}
	}
}
