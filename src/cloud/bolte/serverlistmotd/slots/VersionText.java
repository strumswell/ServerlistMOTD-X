package cloud.bolte.serverlistmotd.slots;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.comphenix.protocol.wrappers.WrappedServerPing;

import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.variables.RandomNumberVariable;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class VersionText {

	private VersionText() {
		throw new IllegalStateException("Utility class");
	}
	
	//Interesting: When VersionName set to null server doesn't respond -> RestrictedMode?
	/**
	 * Set custom protocol version and name with config text
	 * @param ping WrappedServerPing from ProtcolLib
	 */
	public static void activateVersionText(WrappedServerPing ping) {
		ping.setVersionProtocol(-1);
		ping.setVersionName(formatText(SpigotConfig.getVersionText()));
	}
	
	/**
	 * @param versionText unformatted version text
	 * @return formatted version text (vars, color)
	 */
	private static String formatText(String versionText) {
		String formatted = versionText;
		
		String realslots = Bukkit.getServer().getMaxPlayers()+"";
		String realonline = Bukkit.getServer().getOnlinePlayers().size()+"";
		String fakeslots = SpigotConfig.getFakeMaxPlayerNumber()+"";
		String fakeonline = SpigotConfig.getFakeOnlinePlayerNumber()+"";
		
		formatted = ChatColor.translateAlternateColorCodes('&', versionText)
				.replace("%realslots%", realslots)
				.replace("%realonline%", realonline)
				.replace("%fakeonline%", fakeonline)
				.replace("%fakeslots%", fakeslots)
				.replace("%slotsplusone%", SlotsPlusOne.getSlotsPlusOneValue()+"")
				.replace("%randomnumber%", RandomNumberVariable.getRandomNumber()+"");		
		return formatted;
	}
}
