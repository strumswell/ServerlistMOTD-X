package cloud.bolte.serverlistmotd.variables;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
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
	static Random random = new Random();

	public static String getRandomPlayer() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			Player player = (Player) Bukkit.getOnlinePlayers().toArray()
					[random.nextInt(Bukkit.getOnlinePlayers().size())];
			return player.getName();
		} else {
			return getRandomOfflinePlayer();
		}
	}
	
	private static String getRandomOfflinePlayer() {
		if (!(Main.IP_UUID.isEmpty())) {
			ArrayList<UUID> values = new ArrayList<UUID>(Main.IP_UUID.values());
			int index = random.nextInt(values.size());
			if (SpigotConfig.randomPlayerVariableUseTextEnabled()) {
				return SpigotConfig.getRandomPlayerVariableText();
			} else {
				return Bukkit.getOfflinePlayer(values.get(index)).getName();
			}
		} else {
			return "Strumswell";
		}
	}
}
