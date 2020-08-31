package cloud.bolte.serverlistmotd.motd;

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

public class MotdState {
	
	private Motd motdBase;
	private Motd motdExtension;
	private static MotdState instance;

	/**
	 * Initialize motds on object creation
	 */
	private MotdState() {
		initializeMotds();
	}

	/**
	 * Use to get access to object of class
	 * @return singleton object of class
	 */
	public static MotdState getInstance() {
		if (MotdState.instance == null) {
			MotdState.instance = new MotdState();
		}
		return MotdState.instance;
	}
	
	/**
	 * Use to get activated basic motd
	 * @return basic motd (e.g. STANDARD)
	 */
	public Motd getMotdBase() {
		return motdBase;
	}

	/**
	 * Use to get activated motd extensions
	 * @return extions motd (e.g. BAN)
	 */
	public Motd getMotdExtension() {
		return motdExtension;
	}

	/**
	 * Checks the config and sets the enabled motd(-extentsion) 
	 * Very important to use it on config reload! 
	 */
	public void initializeMotds() {
		if (SpigotConfig.randomMotdEnabled()) {
			motdBase = new RandomMotd();
		} else {
			motdBase = new ClassicMotd();
		}

		//From lowest priority to highest!
		motdExtension = null; 
		if (SpigotConfig.banMotdEnabled())
			motdExtension = new BanMotd();
		if (SpigotConfig.banMotdEnabled() && Bukkit.getServer().getPluginManager().getPlugin("MaxBans") != null)
			motdExtension = new MaxBansMotd();
		if (SpigotConfig.banMotdEnabled() && Bukkit.getServer().getPluginManager().getPlugin("BanManager") != null)
			motdExtension = new BanManagerMotd();
		if (SpigotConfig.whitelistMotdEnabled())
			motdExtension = new WhitelistMotd();
		if (SpigotConfig.restrictedModeEnabled())
			motdExtension = new RestrictedModeMotd();
	}
}
