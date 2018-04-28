package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

//UNTESTED

public class MaxBansMotd implements MotdInterface {

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
	
	public void setBanMotd(ServerListPingEvent e, InetAddress ip) {
		//Check if player is known and set motd
		if (Main.IP_UUID.containsKey(ip)) {
			if (MaxBans.instance.getBanManager()
					.getBan(Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName()) != null) {
				e.setMotd(formatMotd(getMOTD(ip), ip));
			}
		}
	}

	@Override
	public String formatMotd(String motd, InetAddress ip) {
		BanInterface ban = new MaxBansPlugin();
		String formattedMotd;

		formattedMotd = ChatColor.translateAlternateColorCodes('&', motd);
		formattedMotd = formattedMotd.replaceAll("%line%", "\n").replaceAll("%weather%", WeatherVariable.getWeather())
				.replaceAll("%time%", TimeVariable.getTime());

		String playerName = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName();

		// FULL BAN
		if (ban.expires(playerName) == 3555L) {
			formattedMotd = formattedMotd.replaceAll("%reason%", ban.banReason(playerName));
		// TEMP BAND	
		} else {
			formattedMotd = formattedMotd
					.replaceAll("%reason%", ban.banReason(playerName))
					.replaceAll("%expdate%", ban.date(playerName)).replaceAll("%exptime%", ban.time(playerName))
					.replaceAll("%expsec%", ban.banExpDateSec(playerName))
					.replaceAll("%expmin%", ban.banExpDateMin(playerName))
					.replaceAll("%exphour%", ban.banExpDateHour(playerName))
					.replaceAll("%expday%", ban.banExpDateDay(playerName))
					.replaceAll("%expmonth%", ban.banExpDateMonth(playerName))
					.replaceAll("%expyear%", ban.banExpDateYear(playerName));
		}	
		return formattedMotd;
	}
}
