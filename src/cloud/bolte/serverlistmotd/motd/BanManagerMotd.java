package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.server.ServerListPingEvent;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.ban.BanInterface;
import cloud.bolte.serverlistmotd.ban.BanManager;
import cloud.bolte.serverlistmotd.variables.TimeVariable;
import cloud.bolte.serverlistmotd.variables.WeatherVariable;
import me.confuser.banmanager.BmAPI;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

//UNTESTED

public class BanManagerMotd implements MotdInterface{

	@Override
	public String getMOTD(InetAddress ip) {
		if (Long.valueOf(BmAPI.getCurrentBan(Main.IP_UUID.get(ip)).getExpires()) != null) {
			return SpigotConfig.getBanTempMotd();
		} else {
			return SpigotConfig.getBanForeverMotd();
		}
	}
	
	public void setBanMotd(ServerListPingEvent e, InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip) && BmAPI.isBanned(Main.IP_UUID.get(ip))) {
				e.setMotd(formatMotd(getMOTD(ip), ip));
		}
	}
	
	@Override
	public String formatMotd(String motd, InetAddress ip) {
		String formattedMotd;

		formattedMotd = ChatColor.translateAlternateColorCodes('&', motd);
		formattedMotd = formattedMotd.replaceAll("%line%", "\n").replaceAll("%weather%", WeatherVariable.getWeather())
				.replaceAll("%time%", TimeVariable.getTime());

		String playerName = Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)).getName();

		BanInterface ban = new BanManager();
		Date timestampconv = new Date((long) ban.expires(playerName) * 1000);

		// FULL BAN
		if (timestampconv.getYear() + 1900 == 1970) {
			formattedMotd = formattedMotd.replaceAll("%reason%", ban.banReason(playerName));
		// TEMP BAN
		} else {
			formattedMotd = formattedMotd.replaceAll("%reason%", ban.banReason(playerName))
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
