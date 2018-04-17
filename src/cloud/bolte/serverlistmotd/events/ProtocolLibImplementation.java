package cloud.bolte.serverlistmotd.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import cloud.bolte.serverlistmotd.Main;
import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.slots.HoverText;
import cloud.bolte.serverlistmotd.slots.OnlineMultiplier;
import cloud.bolte.serverlistmotd.slots.SlotsPlusOne;
import cloud.bolte.serverlistmotd.slots.VersionText;

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
	public ProtocolLibImplementation(Main main) {
		ProtocolLibImplementation.main = main;
	}
	
	public void listenToServerlistPackets() {
		System.out.println("[ServerlistMOTD] Hooking into ProtocolLib.");
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
			PacketAdapter.params(main, PacketType.Status.Server.SERVER_INFO).optionAsync()) {
			
			@Override
			public void onPacketSending(PacketEvent event) {
				WrappedServerPing ping = event.getPacket().getServerPings().read(0);
				
				if (SpigotConfig.fakeMaxPlayerEnabled()) {
					ping.setPlayersMaximum(SpigotConfig.getFakeMaxPlayerNumber());
				}
				
				if (SpigotConfig.fakeOnlinePlayerEnabled()) {
					ping.setPlayersOnline(SpigotConfig.getFakeOnlinePlayerNumber());	
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
				
				if (SpigotConfig.hoverTextEnabled()) {
					HoverText.activateHoverText(ping, main);	
				}	
				
				if (SpigotConfig.restrictedModeEnabled()) {
					ping.setVersionProtocol(-1); //Rest happens in Ping.class
				}
			}
		});
	}
}
