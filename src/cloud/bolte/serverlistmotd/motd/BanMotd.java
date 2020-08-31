package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.server.ServerListPingEvent;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.ban.BanInterface;
import cloud.bolte.serverlistmotd.ban.SpigotBan;
import cloud.bolte.serverlistmotd.variables.RandomPlayerVariable;
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

public class BanMotd implements Motd {

	@Override
	public String getMOTD(InetAddress ip) {
		//TEMP BAN
		if (Bukkit.getBanList(Type.NAME).getBanEntry(Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName())
				.getExpiration() != null) {
			return SpigotConfig.getBanTempMotd();
		//FULL BAN
		} else
			return SpigotConfig.getBanForeverMotd();
	}
	
	@Override
	public String formatMotd(String motd, InetAddress ip) {
		String playerName = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName();
		String formattedMotd;

		formattedMotd = ChatColor.translateAlternateColorCodes('&', motd);
		formattedMotd = formattedMotd.replace("%line%", "\n")
				.replace("%weather%", WeatherVariable.getWeather())
				.replace("%time%", TimeVariable.getTime())
				.replace("%randomplayer%", RandomPlayerVariable.getRandomPlayer());

		BanInterface ban = new SpigotBan();

		// TEMP BAN
		if (Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration() != null) {
			formattedMotd = formattedMotd.replace("%reason%", ban.banReason(playerName))
					.replace("%expdate%", ban.date(playerName))
					.replace("%exptime%", ban.time(playerName))
					.replace("%expsec%", ban.banExpDateSec(playerName))
					.replace("%expmin%", ban.banExpDateMin(playerName))
					.replace("%exphour%", ban.banExpDateHour(playerName))
					.replace("%expday%", ban.banExpDateDay(playerName))
					.replace("%expmonth%", ban.banExpDateMonth(playerName))
					.replace("%expyear%", ban.banExpDateYear(playerName));
		// FULL BAN
		} else formattedMotd = formattedMotd.replace("%reason%", ban.banReason(playerName));
		return formattedMotd;
	}
	
	/**
	 * Sets Ban motd (internal Spigot) 
	 * according to if server knows player, formats and sets it.
	 * 
	 * @param e ServerlistPingEvent from Spigot
	 * @param ip IP of pinging player
	 */
	public void setServerlistMotd(ServerListPingEvent e, InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip)) {
			OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
			if (p.hasPlayedBefore() && p.isBanned()) {
				e.setMotd(formatMotd(getMOTD(ip), ip));
			}
		}
	}
}
