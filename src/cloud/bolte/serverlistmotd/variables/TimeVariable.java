package cloud.bolte.serverlistmotd.variables;

import org.bukkit.Bukkit;

import cloud.bolte.serverlistmotd.SpigotConfig;

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
