package cloud.bolte.serverlistmotd.slots;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.variables.TimeVariable;
import cloud.bolte.serverlistmotd.variables.WeatherVariable;

public class HoverText {
	
	public static void activateHoverText(WrappedServerPing ping, Main main) {
		List<WrappedGameProfile> players = new ArrayList<WrappedGameProfile>();
		for (String string : SpigotConfig.getHoverText()) {
			players.add(new WrappedGameProfile(UUID.randomUUID(), formatText(string)));
		}
		ping.setPlayers(players);
	}
	
	//TODO RANDOM PLAYER
	private static String formatText(String line) {
		line = ChatColor.translateAlternateColorCodes('&', line)
				.replaceAll("%weather%", WeatherVariable.getWeather())
				.replaceAll("%time%", TimeVariable.getTime()
				.replaceAll("%randomplayer%", "55"));
		return line;
	}
}
