package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;

import cloud.bolte.serverlistmotd.util.HexResolver;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.server.ServerListPingEvent;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.util.PapiIntegration;
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

public class ClassicMotd implements Motd{

	@Override
	public String getMOTD(InetAddress ip) {
		if (Main.IP_UUID.containsKey(ip)) {
			return SpigotConfig.getRegularsMotd();
		} else return SpigotConfig.getNewbieMotd();
	}

	@Override
	public String formatMotd(String motd, InetAddress ip) {
		String formattedMotd;
		formattedMotd = ChatColor.translateAlternateColorCodes('&', motd)
				.replace("%line%", "\n")
				.replace("%weather%", WeatherVariable.getWeather())
				.replace("%time%", TimeVariable.getTime())
				.replace("%randomplayer%", RandomPlayerVariable.getRandomPlayer());

		try {
			if (PlayerVariable.isKnownPlayer(ip)) {
				formattedMotd = formattedMotd
						.replace("%player%", PlayerVariable.getNameFromIP(ip))
						.replace("%money%", MoneyVariable.getMoney(ip)+"");
				formattedMotd = PapiIntegration.replaceVariables(Bukkit.getOfflinePlayer(Main.IP_UUID.get(ip)), formattedMotd);
			} else {
				formattedMotd = PapiIntegration.replaceVariables(null, formattedMotd);
			}
		} catch(NullPointerException npe) { }

		formattedMotd = HexResolver.parseHexString(formattedMotd);
		return formattedMotd;
	}

	public void setServerlistMotd(ServerListPingEvent e, InetAddress ip) {
		e.setMotd(formatMotd(getMOTD(ip), ip));
	}
}
