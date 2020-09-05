package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.server.ServerListPingEvent;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.ban.BanInterface;
import cloud.bolte.serverlistmotd.ban.BanManager;
import cloud.bolte.serverlistmotd.util.PapiIntegration;
import cloud.bolte.serverlistmotd.variables.TimeVariable;
import cloud.bolte.serverlistmotd.variables.WeatherVariable;
import me.confuser.banmanager.common.api.BmAPI;
//import me.confuser.banmanager.BmAPI; old version of BanManager

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class BanManagerMotd implements Motd{
	
	@Override
	public String getMOTD(InetAddress ip) {
		if (BmAPI.getCurrentBan(Main.IP_UUID.get(ip)).getExpires() > 0.0) {
			return SpigotConfig.getBanTempMotd();
		} else {
			return SpigotConfig.getBanForeverMotd();
		}
	}
	
	// This needs to be cleaned up
	@Override
	public String formatMotd(String motd, InetAddress ip) {
		String formattedMotd;

		formattedMotd = ChatColor.translateAlternateColorCodes('&', motd);
		formattedMotd = formattedMotd.replace("%line%", "\n")
				.replace("%weather%", WeatherVariable.getWeather())
				.replace("%time%", TimeVariable.getTime());

		OfflinePlayer player = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip));
		String playerName = player.getName();
		
		if(playerName.equals(null)) {
			playerName = "Strumswell";
		}

		BanInterface ban = new BanManager();
		Date timestampconv = new Date((long) ban.expires(playerName) * 1000);

		// FULL BAN
		if (timestampconv.getYear() + 1900 == 1970) {
			formattedMotd = formattedMotd.replace("%reason%", ban.banReason(playerName));
		// TEMP BAN
		} else {
			formattedMotd = formattedMotd.replace("%reason%", ban.banReason(playerName))
					.replace("%expdate%", ban.date(playerName))
					.replace("%exptime%", ban.time(playerName))
					.replace("%expsec%", ban.banExpDateSec(playerName))
					.replace("%expmin%", ban.banExpDateMin(playerName))
					.replace("%exphour%", ban.banExpDateHour(playerName))
					.replace("%expday%", ban.banExpDateDay(playerName))
					.replace("%expmonth%", ban.banExpDateMonth(playerName))
					.replace("%expyear%", ban.banExpDateYear(playerName));
		}
		formattedMotd = PapiIntegration.replaceVariables(player, formattedMotd);
		return formattedMotd;
	}
	
	/**
	 * Sets BanManager motd (external plugin) 
	 * according to if server knows player, formats and sets it.
	 * 
	 * @param e ServerlistPingEvent from Spigot
	 * @param ip IP of pinging player
	 */
	public void setServerlistMotd(ServerListPingEvent e, InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip) && BmAPI.isBanned(Main.IP_UUID.get(ip))) {
			e.setMotd(formatMotd(getMOTD(ip), ip));
		}
	}
}
