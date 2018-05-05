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

public class BanMotd implements MotdInterface {

	/* 
	 * Returns either the temp or fullban motd. 
	 */
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
	
	/*
	 * Sets the motd
	 */
	public void setBanMotd(ServerListPingEvent e, InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip)) {
			OfflinePlayer p = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
			if (p.isBanned()) {
				e.setMotd(formatMotd(getMOTD(ip), ip));
			}
		}
	}
	
	/*
	 * Returns formatted motd with colors
	 * and variables depending on the type
	 */
	@Override
	public String formatMotd(String motd, InetAddress ip) {
		String playerName = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName();
		String formattedMotd;

		formattedMotd = ChatColor.translateAlternateColorCodes('&', motd);
		formattedMotd = formattedMotd.replaceAll("%line%", "\n")
				.replaceAll("%weather%", WeatherVariable.getWeather())
				.replaceAll("%time%", TimeVariable.getTime())
				.replaceAll("%randomplayer%", RandomPlayerVariable.getRandomPlayer());

		BanInterface ban = new SpigotBan();

		// TEMP BAN
		if (Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration() != null) {
			formattedMotd = formattedMotd.replaceAll("%reason%", ban.banReason(playerName))
					.replaceAll("%expdate%", ban.date(playerName)).replaceAll("%exptime%", ban.time(playerName))
					.replaceAll("%expsec%", ban.banExpDateSec(playerName))
					.replaceAll("%expmin%", ban.banExpDateMin(playerName))
					.replaceAll("%exphour%", ban.banExpDateHour(playerName))
					.replaceAll("%expday%", ban.banExpDateDay(playerName))
					.replaceAll("%expmonth%", ban.banExpDateMonth(playerName))
					.replaceAll("%expyear%", ban.banExpDateYear(playerName));
			// FULL BAN
		} else formattedMotd = formattedMotd.replaceAll("%reason%", ban.banReason(playerName));
		return formattedMotd;
	}
}
