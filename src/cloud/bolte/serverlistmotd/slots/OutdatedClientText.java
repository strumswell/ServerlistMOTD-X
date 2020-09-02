package cloud.bolte.serverlistmotd.slots;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.comphenix.protocol.wrappers.WrappedServerPing;

import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.util.PapiIntegration;
import cloud.bolte.serverlistmotd.variables.RandomNumberVariable;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class OutdatedClientText {
	
	private OutdatedClientText() {
		throw new IllegalStateException("Utility class");
	}		
	
	/**
	 * Set custom protocol version name with config text
	 * Triggered when outdated client pings
	 * @param ping WrappedServerPing from ProtcolLib
	 */
	public static void activate(WrappedServerPing ping) {
		ping.setVersionName(formatText(SpigotConfig.getOudatedClientText()));		
	}
	
	/**
	 * @param versionText unformatted version text
	 * @return formatted version text (vars, color)
	 */
	private static String formatText(String versionText) {
		String realslots = Bukkit.getServer().getMaxPlayers()+"";
		String realonline = Bukkit.getServer().getOnlinePlayers().size()+"";
		String fakeslots = SpigotConfig.getFakeMaxPlayerNumber()+"";
		String fakeonline = SpigotConfig.getFakeOnlinePlayerNumber()+"";
		
		String formatted = ChatColor.translateAlternateColorCodes('&', versionText)
				.replace("%realslots%", realslots)
				.replace("%realonline%", realonline)
				.replace("%fakeonline%", fakeonline)
				.replace("%fakeslots%", fakeslots)
				.replace("%slotsplusone%", SlotsPlusOne.getSlotsPlusOneValue()+"")
				.replace("%randomnumber%", RandomNumberVariable.getRandomNumber()+"");	
		formatted = PapiIntegration.replaceVariables(null, formatted);
		
		return formatted;
	}
}
