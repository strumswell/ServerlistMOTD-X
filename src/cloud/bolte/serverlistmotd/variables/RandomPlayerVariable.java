package cloud.bolte.serverlistmotd.variables;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class RandomPlayerVariable {
	private final static Random random = new Random();

	private RandomPlayerVariable() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Get random player name from online players
	 * @return random player name
	 */
	public static String getRandomPlayer() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			Player player = (Player) Bukkit.getOnlinePlayers().toArray()
					[random.nextInt(Bukkit.getOnlinePlayers().size())];
			return player.getName();
		} else {
			return getRandomOfflinePlayer();
		}
	}
	
	/**
	 * Get random player name from IP_UUID hashmap
	 * @return random player name
	 */
	private static String getRandomOfflinePlayer() {
		if (!(Main.IP_UUID.isEmpty())) {
			ArrayList<UUID> values = new ArrayList<>(Main.IP_UUID.values());
			int index = random.nextInt(values.size());
			if (SpigotConfig.randomPlayerVariableUseTextEnabled()) {
				return SpigotConfig.getRandomPlayerVariableText();
			} else {
				OfflinePlayer p = Bukkit.getOfflinePlayer(values.get(index));
				if (p.hasPlayedBefore()) {
					return p.getName();
				}else {
					//User is uncached by Spigot
					//p is null
					return "<unknown>";
				}
			}
		} else {
			//No player joined before
			//Just returning my name ;-)
			return "Strumswell";
		}
	}
}
