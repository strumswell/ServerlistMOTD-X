package cloud.bolte.serverlistmotd.util;

import org.bukkit.Bukkit;
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
	
	 public static Permission perms = null;
	 public static Economy econ = null;
	 public static Chat chat = null;

	 private static boolean setupPermissions() {
	     RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
	     perms = rsp.getProvider();
	     return perms != null;
	 }

	 private static boolean setupChat() {
	     RegisteredServiceProvider<Chat> rsp = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
	     chat = rsp.getProvider();
	     return chat != null;
	 }

	 private static boolean setupEconomy() {
		 if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
			 Bukkit.getLogger().warning("[ServerlistMOTD] Couldn't find Vault. No %money%!");
	         return false;
	     }
		 
		 Bukkit.getLogger().info("[ServerlistMOTD] Hooking into Vault.");

	     RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
	     if (rsp == null) {
			 Bukkit.getLogger().warning("[ServerlistMOTD] Couldn't find Economy-Plugin. No %money%!");
	         return false;
	     }
	     econ = rsp.getProvider();
	     return econ != null;
	 }
	  
	 public static Economy getEcononomy() {
	 	 return econ;
	 }

	 public static Permission getPermissions() {
	 	 return perms;
	 }

	 public static Chat getChat() {
		 return chat;
	 }

	  
	 public static void setupVault() {
		 setupEconomy();
	     setupChat();
		 setupPermissions();
	 }
}