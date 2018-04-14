package cloud.bolte.serverlistmotd.slots;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.comphenix.protocol.wrappers.WrappedServerPing;

import cloud.bolte.serverlistmotd.SpigotConfig;

public class VersionText {
	
	//Interesting: When VersionName set to null server doesn't respond -> RestrictedMode?
	public static void activateVersionText(WrappedServerPing ping) {
		ping.setVersionProtocol(-1);
		ping.setVersionName(formatText(SpigotConfig.getVersionText()));
	}
	
	//TODO: SLOTS PLUS ONE VAR
	private static String formatText(String text) {
		String realslots = Bukkit.getServer().getMaxPlayers()+"";
		String realonline = Bukkit.getServer().getOnlinePlayers().size()+"";
		String fakeslots = SpigotConfig.getFakeMaxPlayerNumber()+"";
		String fakeonline = SpigotConfig.getFakeOnlinePlayerNumber()+"";
		
		text = ChatColor.translateAlternateColorCodes('&', text)
				.replaceAll("%realslots%", realslots)
				.replaceAll("%realonline%", realonline)
				.replaceAll("%fakeonline%", fakeonline)
				.replaceAll("%fakeslots%", fakeslots)
				.replaceAll("%slotsplusone%", "");		
		return text;
	}
}
