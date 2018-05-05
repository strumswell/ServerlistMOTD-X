package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.server.ServerListPingEvent;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class RestrictedModeMotd implements MotdInterface {

	@Override
	public String getMOTD(InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip)) {
			OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
			if (p.isOp()) {
				return SpigotConfig.getRestrictedAccessGrantedMotd();
			} else return SpigotConfig.getRestrictedAccessDeniedMotd();
		} else return SpigotConfig.getRestrictedAccessDeniedMotd();
	}
	
	public void setRestrictedMotd(ServerListPingEvent e, InetAddress ip) {
		e.setMotd(formatMotd(getMOTD(ip), ip));
	}

	@Override
	public String formatMotd(String motd, InetAddress ip) {
		return ChatColor.translateAlternateColorCodes('&', motd)
				.replaceAll("%line%", "\n");
	}
}
