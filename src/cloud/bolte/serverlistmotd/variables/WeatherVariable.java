package cloud.bolte.serverlistmotd.variables;

import org.bukkit.Bukkit;

import cloud.bolte.serverlistmotd.SpigotConfig;

public class WeatherVariable {
	
	/**
	 * Checks weather from world specified in config
	 * @return rain/sun text from config 
	 */
	public static String getWeather() {
		boolean thunder = Bukkit.getServer().getWorld(SpigotConfig.getWeatherWorld()).isThundering();
		boolean rain = Bukkit.getServer().getWorld(SpigotConfig.getWeatherWorld()).hasStorm();

		if (thunder == true || rain == true) {
			return SpigotConfig.getRainText();
		} else {
			return SpigotConfig.getSunText();
		}
	}
}
