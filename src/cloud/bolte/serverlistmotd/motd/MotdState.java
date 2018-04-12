package cloud.bolte.serverlistmotd.motd;

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
	
	/**
	 * Contains standard motds that happen most of the time and are not
	 * "event" based. They are not optional and have to be set.
	 *
	 */
	public enum Motd {
		STANDARD, RANDOM
	}
	
	/**
	 * Contains extensions to the motd that are "event" and player based, 
	 * and therefore don't get triggered most of the time. They extend
	 * the basic motd functionality and are optional.
	 * 
	 * Example: Banned, whitelisted player or the lock down of the server
	 * via restricted mode.
	 *
	 */
	public enum MotdExtension {
		NONE, BAN, WHITELIST, RESTRICTED
	}
	
	public static Motd motd;
	public static MotdExtension motdExtension;	
	
	/**
	 * Set in config enabled motds in static variables on object creation
	 * Needs to be called in onEnable() after SpigotConfig 
	 * 
	 */
	public MotdState () {
		if (SpigotConfig.randomMotdEnabled()) {
			motd = Motd.RANDOM;
		} else motd = Motd.STANDARD;
		
		motdExtension = MotdExtension.NONE; //prevent NPE
		if (SpigotConfig.banMotdEnabled()) motdExtension = MotdExtension.BAN;
		if (SpigotConfig.whitelistMotdEnabled()) motdExtension = MotdExtension.WHITELIST;
		if (SpigotConfig.restrictedModeEnabled()) motdExtension = MotdExtension.RESTRICTED;
	}
}
