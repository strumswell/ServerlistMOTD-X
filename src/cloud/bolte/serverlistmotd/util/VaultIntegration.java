package cloud.bolte.serverlistmotd.util;

import org.bukkit.plugin.RegisteredServiceProvider;

import cloud.bolte.serverlistmotd.Main;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class VaultIntegration {
	
	private static Main main;
	public VaultIntegration(Main main) {
		VaultIntegration.main = main;
	}
	
	 public static Permission permission = null;
	 public static Economy economy = null;
	 public static Chat chat = null;

	 private boolean setupPermissions() {
	      RegisteredServiceProvider<Permission> permissionProvider = main.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	      if (permissionProvider != null) {
	          permission = permissionProvider.getProvider();
	      }
	      return (permission != null);
	  }

	  private boolean setupChat() {
	        RegisteredServiceProvider<Chat> chatProvider = main.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
	        if (chatProvider != null) {
	            chat = chatProvider.getProvider();
	        }

	        return (chat != null);
	  }

	  private boolean setupEconomy() {
	        RegisteredServiceProvider<Economy> economyProvider = main.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            economy = economyProvider.getProvider();
	        }

	        return (economy != null);
	  }
}