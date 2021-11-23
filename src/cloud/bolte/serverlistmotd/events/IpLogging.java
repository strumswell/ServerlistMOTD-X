package cloud.bolte.serverlistmotd.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.util.IO;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class IpLogging implements Listener{
	@EventHandler
	public void onPreLogin(AsyncPlayerPreLoginEvent e) {
		//player not in hashmap
		if (!Main.IP_UUID.containsKey(e.getAddress())) {
			//player with different ip in hashmap
			if (IO.getKeyFromValue(Main.IP_UUID, e.getUniqueId()) != null) {
				//clear old entry and create one with new ip
				Main.IP_UUID.remove(IO.getKeyFromValue(Main.IP_UUID, e.getUniqueId()));
			}
			Main.IP_UUID.put(e.getAddress(), e.getUniqueId());
		}	
	}
}
