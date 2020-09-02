package cloud.bolte.serverlistmotd;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cloud.bolte.serverlistmotd.motd.MotdState;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

// TODO: Migration for OutdatedClientText from older version
public class SpigotConfig {
	private static Main main;

	public SpigotConfig(Main plugin) {
		setPlugin(plugin);
		migrateConfig();
	}
	
	private static void setPlugin(Main plugin) {
		main = plugin;
	}
	/*
	 * ClassicMotd
	 */
	public static String getNewbieMotd() {
		return main.getConfig().getString("ClassicMotd.Newbies");
	}

	public static String getRegularsMotd() {
		return main.getConfig().getString("ClassicMotd.Regulars");
	}
	
	/*
	 * RandomMotd
	 */
	public static List<String> getNewbieRandomMotd() {
		return main.getConfig().getStringList("RandomMotd.Newbies");
	}

	public static List<String> getRegularsRandomMotd() {
		return main.getConfig().getStringList("RandomMotd.Regulars");
	}
	
	public static boolean randomMotdEnabled() {
		return main.getConfig().getBoolean("RandomMotd.Enable");
	}
	
	/*
	 * BanMotd
	 */
	public static String getBanTempMotd() {
		return main.getConfig().getString("BanMotd.MessageTempBan");
	}

	public static String getBanForeverMotd() {
		return main.getConfig().getString("BanMotd.MessageForeverBan");
	}
	
	public static boolean banMotdEnabled() {
		return main.getConfig().getBoolean("BanMotd.Enable");
	}
	
	public static String getFormatDate() {
		return main.getConfig().getString("BanMotd.Format.Date");
	}

	public static String getFormatTime() {
		return main.getConfig().getString("BanMotd.Format.Time");
	}
	
	/*
	 * WhitelistMotd
	 */
	public static String getWhitelistMotd() {
		return main.getConfig().getString("WhitelistMotd.MessageWhitelisted");
	}

	public static String getNotWhitelistedMotd() {
		return main.getConfig().getString("WhitelistMotd.MessageNotWhitelisted");
	}
	
	public static boolean whitelistMotdEnabled() {
		return main.getConfig().getBoolean("WhitelistMotd.Enable");
	}

	/*
	 * RestrictedMode
	 */
	public static String getRestrictedAccessGrantedMotd() {
		return main.getConfig().getString("RestrictedMode.Motd.AccessGranted");
	}

	public static String getRestrictedAccessDeniedMotd() {
		return main.getConfig().getString("RestrictedMode.Motd.AccessDenied");
	}

	public static String getRestrictedKickMessage() {
		return main.getConfig().getString("RestrictedMode.Motd.KickMessage");
	}

	public static String getRestrictedVersionText() {
		return main.getConfig().getString("RestrictedMode.Slots.VersionText");
	}
	
	public static boolean restrictedModeEnabled() {
		return main.getConfig().getBoolean("RestrictedMode.Enable");
	}
	
	/*
	 * FakeMaxPlayer
	 */
	public static boolean fakeMaxPlayerEnabled() {
		return main.getConfig().getBoolean("Slots.FakeMaxPlayer.Enable");
	}
	
	public static int getFakeMaxPlayerNumber() {
		return main.getConfig().getInt("Slots.FakeMaxPlayer.Number");
	}
	
	/*
	 * FakeOnlinePlayer
	 */
	public static boolean fakeOnlinePlayerEnabled() {
		return main.getConfig().getBoolean("Slots.FakeOnlinePlayer.Enable");
	}
	
	public static boolean fakeOnlinePlayerRandomNumberEnabled() {
		return main.getConfig().getBoolean("Slots.FakeOnlinePlayer.RandomNumber.Enable");
	}
	
	public static int getfakeOnlinePlayerRandomNumberMax() {
		return main.getConfig().getInt("Slots.FakeOnlinePlayer.RandomNumber.Max");
	}
	
	public static int getfakeOnlinePlayerRandomNumberMin() {
		return main.getConfig().getInt("Slots.FakeOnlinePlayer.RandomNumber.Min");
	}
	
	public static int getFakeOnlinePlayerNumber() {
		return main.getConfig().getInt("Slots.FakeOnlinePlayer.Number");
	}
	
	/*
	 * VersionText
	 */
	public static String getVersionText() {
		return main.getConfig().getString("Slots.VersionText.Message");
	}
	
	public static boolean versionTextEnabled() {
		return main.getConfig().getBoolean("Slots.VersionText.Enable");
	}
	
	/*
	 * OutdatedClientText
	 */
	public static String getOudatedClientText() {
		return main.getConfig().getString("Slots.OutdatedClientText.Message");
	}
	
	public static boolean outdatedClientTextEnabled() {
		return main.getConfig().getBoolean("Slots.OutdatedClientText.Enable");
	}
	
	/*
	 * UnkownSlots
	 */
	public static boolean unknownSlotsEnabled() {
		return main.getConfig().getBoolean("Slots.UnknownSlots.Enable");
	}
	
	/*
	 * SlotsPlusOne
	 */
	public static boolean slotsPlusOneEnabled() {
		return main.getConfig().getBoolean("Slots.SlotsPlusOne.Enable");
	}
	
	public static int getSlotsPlusOneMinSlots() {
		return main.getConfig().getInt("Slots.SlotsPlusOne.MinSlots");
	}

	public static int getSlotsPlusOneMaxSlots() {
		return main.getConfig().getInt("Slots.SlotsPlusOne.MaxSlots");
	}

	public static int getSlotsPlusOneAddSlotsToOnline() {
		return main.getConfig().getInt("Slots.SlotsPlusOne.AddSlotsToOnline");
	}

	/*
	 * OnlineMultiplier
	 */
	public static boolean onlineMultiplierEnabled() {
		return main.getConfig().getBoolean("Slots.OnlineMultiplier.Enable");
	}
	
	public static int getOnlineMultiplierMinSlots() {
		return main.getConfig().getInt("Slots.OnlineMultiplier.MinSlots");
	}

	public static int getOnlineMultiplierMaxSlots() {
		return main.getConfig().getInt("Slots.OnlineMultiplier.MaxSlots");
	}
	
	public static int getOnlineMultiplierAddSlots() {
		return main.getConfig().getInt("Slots.OnlineMultiplier.AddSlotsWhenOnline>MinSlots");
	}

	public static double getOnlineMultiplier() {
		return main.getConfig().getDouble("Slots.OnlineMultiplier.MultiplyBy");
	}
	
	/*
	 * HoverText
	 */
	public static List<String> getHoverText() {
		return main.getConfig().getStringList("Slots.HoverText.Messages");
	}
	
	public static boolean hoverTextEnabled() {
		return main.getConfig().getBoolean("Slots.HoverText.Enable");
	}

	/*
	 * TimeVar
	 */
	public static String getTimeWorld() {
		return main.getConfig().getString("Variables.TimeVariable.World");
	}

	public static String getDayText() {
		return main.getConfig().getString("Variables.TimeVariable.DayText");
	}

	public static String getNightText() {
		return main.getConfig().getString("Variables.TimeVariable.NightText");
	}
	
	/*
	 * WeatherVar
	 */
	public static String getRainText() {
		return main.getConfig().getString("Variables.WeatherVariable.RainText");
	}

	public static String getSunText() {
		return main.getConfig().getString("Variables.WeatherVariable.SunText");
	}

	public static String getWeatherWorld() {
		return main.getConfig().getString("Variables.WeatherVariable.World");
	}

	/*
	 * RandomNumberVar
	 */
	public static int getRandomMax() {
		return main.getConfig().getInt("Variables.RandomNumberVariable.Max");
	}
	
	public static int getRandomMin() {
		return main.getConfig().getInt("Variables.RandomNumberVariable.Min");
	}
	
	/*
	 * RandomPlayerVar
	 */
	public static String getRandomPlayerVariableText() {
		return main.getConfig().getString("Variables.RandomPlayerVariable.UseTextWhenNobodyOnline.Text");
	}

	public static boolean randomPlayerVariableUseTextEnabled() {
		return main.getConfig().getBoolean("Variables.RandomPlayerVariable.UseTextWhenNobodyOnline.Enable");
	}

	public static boolean randomPlayerVariableUseDBEnabled() {
		return main.getConfig().getBoolean("Variables.RandomPlayerVariable.UseDatabaseNameWhenNobodyOnline.Enable");
	}

	/*
	 * ConfigSave
	 */

	public static int autoSaveIntervalInMin() {
		return main.getConfig().getInt("AutoSaveConfig.IntervalInMin");
	}

	/*
	 * SET BOOLEAN
	 */

	public static void toggleRestrictedMode(CommandSender sender) {
		if (SpigotConfig.restrictedModeEnabled()) {
			main.getConfig().set("RestrictedMode.Enable", false);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cRestrictedMode toggled off!");
		} else {
			main.getConfig().set("RestrictedMode.Enable", true);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cRestrictedMode toggled on!");
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!(p.isOp() || p.hasPermission("serverlist.restrictedmode.nokick"))) {
					p.kickPlayer(SpigotConfig.getRestrictedKickMessage());
				}
			}
		}
		SpigotConfig.saveSmotdConfig();
	}

	public static void toggleVersionText(CommandSender sender) {
		if (SpigotConfig.versionTextEnabled()) {
			main.getConfig().set("Slots.VersionText.Enable", false);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cVersionText toggled off!");
		} else {
			main.getConfig().set("Slots.VersionText.Enable", true);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cVersionText toggled on!");
		}
		SpigotConfig.saveSmotdConfig();
	}

	public static void toggleRandomMotd(CommandSender sender) {
		if (SpigotConfig.randomMotdEnabled()) {
			main.getConfig().set("RandomMotd.Enable", false);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cRandomMotd toggled off!");
		} else {
			main.getConfig().set("RandomMotd.Enable", true);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cRandomMotd toggled on!");
		}
		SpigotConfig.saveSmotdConfig();
	}

	public static void toggleBanMotd(CommandSender sender) {
		if (SpigotConfig.banMotdEnabled()) {
			main.getConfig().set("BanMotd.Enable", false);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cBanMotd toggled off!");
		} else {
			main.getConfig().set("BanMotd.Enable", true);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cBanMotd toggled on!");
		}
		SpigotConfig.saveSmotdConfig();
	}

	public static void toggleWhitelistMotd(CommandSender sender) {
		if (SpigotConfig.whitelistMotdEnabled()) {
			main.getConfig().set("WhitelistMotd.Enable", false);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cWhitelistMotd toggled off!");
		} else {
			main.getConfig().set("WhitelistMotd.Enable", true);
			sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cWhitelistMotd toggled on!");
		}
		SpigotConfig.saveSmotdConfig();
	}

	/*
	 * UTIL
	 */

	/**
	 * Reload config and inform MotdState of possible changes
	 * @param sender Sender
	 */
	public static void reloadSmotdConfig(CommandSender sender) {
		main.reloadConfig();
		sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cConfig reloaded!");
		MotdState.getInstance().initializeMotds();
	}

	/**
	 * Save config and inform MotdState of possible changes 
	 */
	public static void saveSmotdConfig() {
		main.saveConfig();
		MotdState.getInstance().initializeMotds();
	}


	/**
	 * Checks if world name set in config is existent 
	 * And fixes problems automatically
	 */
	public void configWorldCheck() {
		if (Bukkit.getWorld(getWeatherWorld()) == null || Bukkit.getWorld(getTimeWorld()) == null) {
			System.out.println("[ServerlistMOTD] ------------------------");
			//Informing user of mismatch
			Bukkit.getLogger().severe(
					"[ServerlistMOTD] CAN'T FIND THE DEFINED WORLD FROM YOUR CONFIG!");
			System.out.println("[ServerlistMOTD] Searching for available world...");
			
			//Search shortest world name
			String worldName = "";
			for(World w : Bukkit.getServer().getWorlds()) {
				if (worldName.equalsIgnoreCase("") | w.getName().length() < worldName.length()) {
					worldName = w.getName();
				}
			}
			
			//If found world is not correct disable plugin and return
			if (Bukkit.getWorld(worldName) == null || Bukkit.getWorld(worldName) == null) {
				Bukkit.getPluginManager().disablePlugin(main);
				return;
			}
			
			//Update config with found world name
			main.getConfig().set("Variables.TimeVariable.World", worldName);
			main.getConfig().set("Variables.WeatherVariable.World", worldName);
			SpigotConfig.saveSmotdConfig();
			System.out.println("[ServerlistMOTD] Found '" + worldName + "‘ and saved it to config.");
			System.out.println("[ServerlistMOTD] We're good now. ;-)");
			System.out.println("[ServerlistMOTD] ------------------------");
		}
	}
	
	/**
	 * Rename old config (if available) and write new one
	 */
	public static void migrateConfig() {
		if (!main.getConfig().isSet("DoNOTtouchMe") || main.getConfig().getDouble("DoNOTtouchMe") == 5.0) {
			File oldConfig = new File(main.getDataFolder(), "config.yml");
			File newFile = new File(main.getDataFolder(), "config_old.yml");
			
			if (newFile.exists()) {
				System.out.println("[ServerlistMOTD] Remove your old config.yml!");
				Bukkit.getPluginManager().disablePlugin(main);
			}
			
			boolean fileRenamed = oldConfig.renameTo(newFile);
			if (fileRenamed) {
				main.saveDefaultConfig();
				main.reloadConfig();
				System.out.println("[ServerlistMOTD] Renamed old config and created new config!");
			}
		}
		if (main.getConfig().getDouble("DoNOTtouchMe") == 10.0) {
			System.out.println("[ServerlistMOTD] Adding new features to config.yml...");
			main.getConfig().set("Slots.OutdatedClientText.Enable", false);
			main.getConfig().set("Slots.OutdatedClientText.Message", "Use Minecraft 1.16 &r&7%realonline%&8/&7%realslots%");
			main.getConfig().set("DoNOTtouchMe", 10.1);
			saveSmotdConfig();
			System.out.println("[ServerlistMOTD] Config successfully migrated to new version.");
		}
	}
}
