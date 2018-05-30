package cloud.bolte.serverlistmotd.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class VaultIntegration {

	public static Permission perms = null;
	public static Economy econ = null;
	public static Chat chat = null;

	private static boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager()
				.getRegistration(Permission.class);
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
		if (setupEconomy()){
			setupChat();
			setupPermissions();
		}
	}
}