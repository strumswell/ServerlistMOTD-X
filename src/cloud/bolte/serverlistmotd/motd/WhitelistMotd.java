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

public class WhitelistMotd implements MotdInterface {

	@Override
	public String getMOTD(InetAddress ip) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
		if (Bukkit.getWhitelistedPlayers().contains(p) || p.isOp()) {
			return SpigotConfig.getWhitelistMotd();
		} else return SpigotConfig.getNotWhitelistedMotd();
	}
	
	public void setWhitelistMotd(ServerListPingEvent e, InetAddress ip) {
		if (Bukkit.hasWhitelist()) {
			if (Main.IP_UUID.containsKey(ip)) {
				e.setMotd(formatMotd(getMOTD(ip), ip));
			} else e.setMotd(formatMotd(SpigotConfig.getNotWhitelistedMotd(), ip));
		}
	}

	@Override
	public String formatMotd(String motd, InetAddress ip) {
		String formattedMotd = ChatColor.translateAlternateColorCodes('&', motd).replaceAll("%line%", "\n");
		if (Main.IP_UUID.containsKey(ip)) {
			OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
			formattedMotd = formattedMotd.replaceAll("%player%", p.getName());
		}
		return formattedMotd;
	}
}
