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
	
	/*
	 * Contain enabled motd(-extensions) from config to easily check which motd is
	 * enabled
	 */
	private Motd motd;
	private MotdExtension motdExtension;

	/**
	 * Contains standard motds that happen most of the time and are not "event"
	 * based. They are not optional and have to be set.
	 *
	 */
	public enum Motd {
		STANDARD, RANDOM
	}

	/**
	 * Contains extensions to the motd that are "event" and player based, and
	 * therefore don't get triggered most of the time. They extend the basic motd
	 * functionality and are optional.
	 * 
	 * Example: Banned, whitelisted player or the lock down of the server via
	 * restricted mode.
	 *
	 */
	public enum MotdExtension {
		NONE, BAN_SPIGOT, BAN_MAXBANS, BAN_BANMANAGER, WHITELIST, RESTRICTED
	}

	private static MotdState instance;

	/* private constructor to avoid instantiation from other classes */
	private MotdState() {
		initializeMotds();
	}

	/* return private singleton object */
	public static MotdState getInstance() {
		if (MotdState.instance == null) {
			MotdState.instance = new MotdState();
		}
		return MotdState.instance;
	}

	public Motd getMotd() {
		return motd;
	}

	public MotdExtension getMotdExtensions() {
		return motdExtension;
	}

	/*
	 * Checks the config and sets the enabled motd(-extentsion) Very important to
	 * use it on config reload!
	 */
	public void initializeMotds() {
		if (SpigotConfig.randomMotdEnabled()) {
			motd = Motd.RANDOM;
		} else
			motd = Motd.STANDARD;

		/*
		 * From lowest priority to highest! If Ban and Restricted are enabled in config,
		 * Restricted will always be enabled. If Ban and Whitelist are enabled in
		 * config, Whitelist will always be enabled. If Whitelist and Restricted are
		 * enabled in config, Restricted will always be enabled.
		 * 
		 */
		motdExtension = MotdExtension.NONE; // prevent NPE
		if (SpigotConfig.banMotdEnabled())
			motdExtension = MotdExtension.BAN_SPIGOT;
		if (Bukkit.getServer().getPluginManager().getPlugin("MaxBans") != null)
			motdExtension = MotdExtension.BAN_MAXBANS;
		if (Bukkit.getServer().getPluginManager().getPlugin("BanManager") != null)
			motdExtension = MotdExtension.BAN_BANMANAGER;
		if (SpigotConfig.whitelistMotdEnabled())
			motdExtension = MotdExtension.WHITELIST;
		if (SpigotConfig.restrictedModeEnabled())
			motdExtension = MotdExtension.RESTRICTED;
	}
}
