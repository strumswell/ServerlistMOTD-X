package cloud.bolte.serverlistmotd.slots;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
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

public class HoverText {
	
	public static void activateHoverText(WrappedServerPing ping, Main main) {
		List<WrappedGameProfile> players = new ArrayList<WrappedGameProfile>();
		for (String string : SpigotConfig.getHoverText()) {
			players.add(new WrappedGameProfile(UUID.randomUUID(), formatText(string)));
		}
		ping.setPlayers(players);
	}
	
	private static String formatText(String hoverLine) {
		String formatted = hoverLine;
		formatted = ChatColor.translateAlternateColorCodes('&', hoverLine)
				.replaceAll("%weather%", WeatherVariable.getWeather())
				.replaceAll("%time%", TimeVariable.getTime()
				.replaceAll("%randomplayer%", RandomPlayerVariable.getRandomPlayer()));
		return formatted;
	}
}
