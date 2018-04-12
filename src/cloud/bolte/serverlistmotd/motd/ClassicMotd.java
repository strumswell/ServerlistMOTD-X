package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

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

public class ClassicMotd implements MotdInterface{

	@Override
	public String getMOTD(InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip)) {
			return SpigotConfig.getRegularsMotd();
		} else return SpigotConfig.getNewbieMotd();
	}

	@Override
	public String formatMotd(String motd, InetAddress ip) {
		String formattedMotd;
		formattedMotd = ChatColor.translateAlternateColorCodes('&', motd);
		formattedMotd = formattedMotd.replaceAll("%line%", "\n")
		.replaceAll("%weather%", "clear")
		.replaceAll("%time%", "day");
		
		if (Main.IP_UUID.containsKey(ip)) {
			OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
			formattedMotd = formattedMotd.replaceAll("%player%", p.getName());
		}
		return formattedMotd;
	}
}
