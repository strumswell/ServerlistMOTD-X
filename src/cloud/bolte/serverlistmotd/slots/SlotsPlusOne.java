package cloud.bolte.serverlistmotd.slots;

import org.bukkit.Bukkit;

import com.comphenix.protocol.wrappers.WrappedServerPing;

import cloud.bolte.serverlistmotd.SpigotConfig;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class SlotsPlusOne {
	
	/**
	 * Set slots and online players according to SlotsPlusOne settings in config.
	 * @param ping Ping object from ProtocolLib
	 */
	public static void acitvateSlotsPlusOne(WrappedServerPing ping) {
		int onlinenumber = Bukkit.getOnlinePlayers().size();
	
		if (onlinenumber >= SpigotConfig.getSlotsPlusOneMinSlots()) {
			ping.setPlayersMaximum(onlinenumber + SpigotConfig.getSlotsPlusOneAddSlotsToOnline());
		} else {
			ping.setPlayersMaximum(SpigotConfig.getSlotsPlusOneMinSlots());
		}

		if (onlinenumber == SpigotConfig.getSlotsPlusOneMaxSlots()) {
			ping.setPlayersMaximum(SpigotConfig.getSlotsPlusOneMaxSlots());
		}
	}
	
	/**
	 * Get a SlotsPlusOne value for further usage
	 * @return SlotsPlusOne value
	 */
	public static int getSlotsPlusOneValue() {
		int onlinenumber = Bukkit.getOnlinePlayers().size();
		
		if (onlinenumber >= SpigotConfig.getSlotsPlusOneMinSlots()) {
			return onlinenumber + SpigotConfig.getSlotsPlusOneAddSlotsToOnline();
		} else if (onlinenumber == SpigotConfig.getSlotsPlusOneMaxSlots()) {
			return SpigotConfig.getSlotsPlusOneMaxSlots();
		} else {
			return SpigotConfig.getSlotsPlusOneMinSlots();
		}
	}
}
