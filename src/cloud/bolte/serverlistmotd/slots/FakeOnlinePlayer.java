package cloud.bolte.serverlistmotd.slots;

import java.util.Random;

import com.comphenix.protocol.wrappers.WrappedServerPing;

import static cloud.bolte.serverlistmotd.SpigotConfig.fakeOnlinePlayerRandomNumberEnabled;
import static cloud.bolte.serverlistmotd.SpigotConfig.getfakeOnlinePlayerRandomNumberMax;
import static cloud.bolte.serverlistmotd.SpigotConfig.getfakeOnlinePlayerRandomNumberMin;
import static cloud.bolte.serverlistmotd.SpigotConfig.getFakeOnlinePlayerNumber;;

public class FakeOnlinePlayer {
	
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
