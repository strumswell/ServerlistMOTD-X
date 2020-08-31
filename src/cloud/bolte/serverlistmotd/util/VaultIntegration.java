package cloud.bolte.serverlistmotd.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class VaultIntegration {

	private VaultIntegration() {
		throw new IllegalStateException("Utility class");
	}

	public static Economy econ = null;

	public static boolean setupEconomy() {
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
		Bukkit.getLogger().info("[ServerlistMOTD] Using "+econ+" via Vault.");
		return econ != null;
	}

	public static Economy getEcononomy() {
		return econ;
	}
}