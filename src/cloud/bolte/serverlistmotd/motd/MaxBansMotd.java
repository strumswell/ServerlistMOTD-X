package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.server.ServerListPingEvent;
import org.maxgamer.maxbans.MaxBans;
import org.maxgamer.maxbans.banmanager.TempBan;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.ban.BanInterface;
import cloud.bolte.serverlistmotd.ban.MaxBansPlugin;
import cloud.bolte.serverlistmotd.variables.TimeVariable;
import cloud.bolte.serverlistmotd.variables.WeatherVariable;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class MaxBansMotd implements Motd {
	
	@Override
	public String getMOTD(InetAddress ip) {
		//Check for temp or full ban
		if (MaxBans.instance.getBanManager()
		.getBan(Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName()) instanceof TempBan) {
			return SpigotConfig.getBanTempMotd();
		} else {
			return SpigotConfig.getBanForeverMotd();
		}
	}
	
	@Override
	public String formatMotd(String motd, InetAddress ip) {
		BanInterface ban = new MaxBansPlugin();
		String formattedMotd;

		formattedMotd = ChatColor.translateAlternateColorCodes('&', motd);
		formattedMotd = formattedMotd.replace("%line%", "\n")
				.replace("%weather%", WeatherVariable.getWeather())
				.replace("%time%", TimeVariable.getTime());

		String playerName = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName();

		// FULL BAN
		if (ban.expires(playerName) == 3555L) {
			formattedMotd = formattedMotd.replace("%reason%", ban.banReason(playerName));
		// TEMP BAND	
		} else {
			formattedMotd = formattedMotd
					.replace("%reason%", ban.banReason(playerName))
					.replace("%expdate%", ban.date(playerName)).replace("%exptime%", ban.time(playerName))
					.replace("%expsec%", ban.banExpDateSec(playerName))
					.replace("%expmin%", ban.banExpDateMin(playerName))
					.replace("%exphour%", ban.banExpDateHour(playerName))
					.replace("%expday%", ban.banExpDateDay(playerName))
					.replace("%expmonth%", ban.banExpDateMonth(playerName))
					.replace("%expyear%", ban.banExpDateYear(playerName));
		}	
		return formattedMotd;
	}
	
	/**
	 * Sets MaxBans motd (external plugin) motd according to 
	 * if server knows player, formats and sets it.
	 * 
	 * @param e ServerlistPingEvent from Spigot
	 * @param ip IP of pinging player
	 */
	public void setServerlistMotd(ServerListPingEvent e, InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip)) {
			//Check if player is known and set motd
			OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
			if (p.hasPlayedBefore()) {
				if (Main.IP_UUID.containsKey(ip) && MaxBans.instance.getBanManager().getBan(p.getName()) != null) {
					e.setMotd(formatMotd(getMOTD(ip), ip));
				}
			}
		}
	}
}
