package cloud.bolte.serverlistmotd.events;

import java.net.InetAddress;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import cloud.bolte.serverlistmotd.motd.MotdState;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class Ping implements Listener {	
	/*
	 * Set motds according to config
	 */
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		InetAddress ip = e.getAddress();
		// Set standard motd
		MotdState.getInstance().getMotdBase().setServerlistMotd(e, ip);
		// Allow motd extension to overwrite motd
		if (MotdState.getInstance().getMotdExtension() != null) {
			MotdState.getInstance().getMotdExtension().setServerlistMotd(e, ip);
		}
	}
}
