package cloud.bolte.serverlistmotd.events;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.slots.FakeOnlinePlayer;
import cloud.bolte.serverlistmotd.slots.HoverText;
import cloud.bolte.serverlistmotd.slots.OnlineMultiplier;
import cloud.bolte.serverlistmotd.slots.OutdatedClientText;
import cloud.bolte.serverlistmotd.slots.SlotsPlusOne;
import cloud.bolte.serverlistmotd.slots.VersionText;
import org.bukkit.Bukkit;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class ProtocolLibImplementation {
	private static Main main;

	public ProtocolLibImplementation(Main plugin) {
		setPlugin(plugin);
	}
	
	private static void setPlugin(Main plugin) {
		main = plugin;
	}

	public void setupIntegration() {
		Bukkit.getLogger().info("[ServerlistMOTD] Hooking into ProtocolLib.");
		ProtocolLibrary.getProtocolManager().addPacketListener(
				new PacketAdapter(PacketAdapter.params(main, PacketType.Status.Server.SERVER_INFO).optionAsync()) {
					@Override
					public void onPacketSending(PacketEvent event) {
						WrappedServerPing ping = event.getPacket().getServerPings().read(0);

						if (SpigotConfig.fakeMaxPlayerEnabled()) {
							ping.setPlayersMaximum(SpigotConfig.getFakeMaxPlayerNumber());
						}

						if (SpigotConfig.fakeOnlinePlayerEnabled()) {
							FakeOnlinePlayer.activateFakeOnlinePlayer(ping);
						}
						
						if (SpigotConfig.outdatedClientTextEnabled()) {
							OutdatedClientText.activate(ping);
						}

						if (SpigotConfig.versionTextEnabled()) {
							VersionText.activateVersionText(ping);
						}

						if (SpigotConfig.slotsPlusOneEnabled()) {
							SlotsPlusOne.acitvateSlotsPlusOne(ping);
						}

						if (SpigotConfig.onlineMultiplierEnabled()) {
							OnlineMultiplier.activateOnlineMultiplier(ping);
						}

						if (SpigotConfig.unknownSlotsEnabled()) {
							ping.setPlayersVisible(false);
						}

						// Unknown slots won't work if hover text is enabled as well. Result: 0/0 instead of ???
						// I won't introduce a check here to not introduce unexpected behaviour with existing config.ymls
						if (SpigotConfig.hoverTextEnabled()) { //  && !SpigotConfig.unknownSlotsEnabled()
							HoverText.activateHoverText(ping);
						}

						if (SpigotConfig.restrictedModeEnabled()) {
							ping.setVersionProtocol(-1);
							ping.setVersionName(SpigotConfig.getRestrictedVersionText()); // Rest happens in Ping.class
						}		
					}
				});
	}
}
