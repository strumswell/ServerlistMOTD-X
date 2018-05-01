package cloud.bolte.serverlistmotd.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import cloud.bolte.serverlistmotd.SpigotConfig;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class RestrictedModeJoin implements Listener{
	
    @EventHandler
	public void logInRestrictedMode(PlayerLoginEvent e) {
    	if (SpigotConfig.restrictedModeEnabled()) {
    		if (e.getPlayer().isOp() || e.getPlayer().hasPermission("ServerlistMOTD.restricted.join")) {
    			e.allow();	
    		} else e.disallow(Result.KICK_OTHER, 
    				ChatColor.translateAlternateColorCodes('&', SpigotConfig.getRestrictedKickMessage()));	
		}
	}
}
