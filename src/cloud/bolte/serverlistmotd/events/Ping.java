package cloud.bolte.serverlistmotd.events;

import java.net.InetAddress;

import cloud.bolte.serverlistmotd.motd.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class Ping implements Listener {
	private Motd motd;
	
	/*
	 * Set motds according to config
	 */
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		InetAddress ip = e.getAddress();
		
		// Motds have to be loaded from config
		if (MotdState.getInstance().getMotd() != null) {
			switch (MotdState.getInstance().getMotd()) {
			case STANDARD:
				motd = new ClassicMotd();
				e.setMotd(motd.formatMotd(motd.getMOTD(ip), ip));
				break;
			case RANDOM:
				motd = new RandomMotd();
				e.setMotd(motd.formatMotd(motd.getMOTD(ip), ip));
				break;
			default:
				break;
			}
		} else System.out.println("[ServerlistMOTD] Oooops. Something went wrong creating the MOTD!");

		// Motd extensions have to be loaded from config
		if (MotdState.getInstance().getMotdExtensions() != null) {
			switch (MotdState.getInstance().getMotdExtensions()) {
			case BAN_SPIGOT:
				BanMotd banmotd = new BanMotd();
				banmotd.setBanMotd(e, ip);
				break;
			case BAN_MAXBANS:
				MaxBansMotd maxbanmotd = new MaxBansMotd();
				maxbanmotd.setBanMotd(e, ip);
				break;
			case BAN_BANMANAGER:
				BanManagerMotd banmanagermotd = new BanManagerMotd();
				banmanagermotd.setBanMotd(e, ip);
				break;
			case WHITELIST:
				WhitelistMotd whitemotd = new WhitelistMotd();
				whitemotd.setWhitelistMotd(e, ip);
				break;
			case RESTRICTED:
				RestrictedModeMotd restrictedmotd = new RestrictedModeMotd();
				restrictedmotd.setRestrictedMotd(e, ip);
				break;
			default:
				break;
			}
		} else System.out.println("[ServerlistMOTD] Oooops. Something went wrong creating the MOTD!");
	}
}
