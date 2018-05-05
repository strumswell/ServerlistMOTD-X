package cloud.bolte.serverlistmotd.events;

import java.net.InetAddress;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import cloud.bolte.serverlistmotd.motd.BanMotd;
import cloud.bolte.serverlistmotd.motd.ClassicMotd;
import cloud.bolte.serverlistmotd.motd.MotdInterface;
import cloud.bolte.serverlistmotd.motd.MotdState;
import cloud.bolte.serverlistmotd.motd.RandomMotd;
import cloud.bolte.serverlistmotd.motd.RestrictedModeMotd;
import cloud.bolte.serverlistmotd.motd.WhitelistMotd;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class Ping implements Listener {
	private MotdInterface motd;
	
	/*
	 * Set motds according to config
	 */
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		InetAddress ip = e.getAddress();

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

		if (MotdState.getInstance().getMotdExtensions() != null) {
			switch (MotdState.getInstance().getMotdExtensions()) {
			case BAN_SPIGOT:
				BanMotd banmotd = new BanMotd();
				banmotd.setBanMotd(e, ip);
				break;
			case BAN_MAXBANS:
				BanMotd maxbanmotd = new BanMotd();
				maxbanmotd.setBanMotd(e, ip);
				break;
			case BAN_BANMANAGER:
				BanMotd banmanagermotd = new BanMotd();
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
