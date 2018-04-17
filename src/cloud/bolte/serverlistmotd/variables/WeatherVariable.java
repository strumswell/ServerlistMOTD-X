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
