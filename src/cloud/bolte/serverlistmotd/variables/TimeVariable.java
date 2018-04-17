package cloud.bolte.serverlistmotd.variables;

import org.bukkit.Bukkit;

import cloud.bolte.serverlistmotd.SpigotConfig;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class TimeVariable {
	/**
	 * Checks wethaer in world specified in config
	 * @return day/night text from config
	 */
	public static String getTime() {
		String currentTime = Bukkit.getServer().getWorld(SpigotConfig.getTimeWorld()).getTime()+"";
		Integer time = Integer.valueOf(currentTime);

		if (time < 12000) {
			return SpigotConfig.getDayText();
		} else {
			return SpigotConfig.getNightText();
		}
	}
}
