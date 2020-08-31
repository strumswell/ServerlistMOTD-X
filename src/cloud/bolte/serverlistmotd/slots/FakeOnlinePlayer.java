package cloud.bolte.serverlistmotd.slots;

import java.util.Random;

import com.comphenix.protocol.wrappers.WrappedServerPing;

import static cloud.bolte.serverlistmotd.SpigotConfig.fakeOnlinePlayerRandomNumberEnabled;
import static cloud.bolte.serverlistmotd.SpigotConfig.getfakeOnlinePlayerRandomNumberMax;
import static cloud.bolte.serverlistmotd.SpigotConfig.getfakeOnlinePlayerRandomNumberMin;
import static cloud.bolte.serverlistmotd.SpigotConfig.getFakeOnlinePlayerNumber;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class FakeOnlinePlayer {

	private FakeOnlinePlayer() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Activate the FakeOnlinePlayer feature which fakes the online 
	 * player count in the serverlist. 
	 * @param WrappedServerPing object from ProtocolLib
	 */
	public static void activateFakeOnlinePlayer(WrappedServerPing ping) {
		if (!fakeOnlinePlayerRandomNumberEnabled()) {
			ping.setPlayersOnline(getFakeOnlinePlayerNumber());
		} else {
			Random random = new Random();
			ping.setPlayersOnline(random.nextInt(getfakeOnlinePlayerRandomNumberMax()+1 - getfakeOnlinePlayerRandomNumberMin()) 
					+ getfakeOnlinePlayerRandomNumberMin());	
		}
	}

}
