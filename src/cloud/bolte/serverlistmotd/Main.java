package cloud.bolte.serverlistmotd;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import cloud.bolte.serverlistmotd.events.IpLogging;
import cloud.bolte.serverlistmotd.events.Ping;
import cloud.bolte.serverlistmotd.events.RestrictedModeJoin;
import cloud.bolte.serverlistmotd.motd.MotdState;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

//TODO: Slots Gedöns
//TODO: HashMapSaver, SaveTimer, Commands

public class Main extends JavaPlugin implements Listener {
	public static Map<InetAddress, UUID> IP_UUID = new HashMap<InetAddress, UUID>();
	
	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		saveDefaultConfig();
		SpigotConfig config = new SpigotConfig(this);
		MotdState state = new MotdState();

		Bukkit.getServer().getPluginManager().registerEvents(new Ping(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new IpLogging(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new RestrictedModeJoin(), this);
	} 	
}
