package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;

import org.bukkit.ChatColor;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.variables.MoneyVariable;
import cloud.bolte.serverlistmotd.variables.PlayerVariable;
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
		.replaceAll("%weather%", WeatherVariable.getWeather())
		.replaceAll("%time%", TimeVariable.getTime()
		.replaceAll("%randomplayer%", RandomPlayerVariable.getRandomPlayer()));
		
		if (PlayerVariable.isKnownPlayer(ip)) {
			formattedMotd = formattedMotd
					.replaceAll("%player%", PlayerVariable.getNameFromIP(ip)
					.replaceAll("%money%", MoneyVariable.getMoney(ip)+""));
		}
		return formattedMotd;
	}
}
