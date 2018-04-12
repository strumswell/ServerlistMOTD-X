package cloud.bolte.serverlistmotd;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class SpigotConfig {	
	private static Main main;
	public SpigotConfig(Main main) {
		SpigotConfig.main = main;
	}
	
	public static String getNewbieMotd() {
		return main.getConfig().getString("Motd.Message.Newbies");
	}

	public static String getRegularsMotd() {
		return main.getConfig().getString("Motd.Message.Regulars");
	}

	public static String getBanForeverMotd() {
		return main.getConfig().getString("Motd.BanMessage.MessageForeverBan");
	}
	
	public static String getBanTempMotd() {
		return main.getConfig().getString("Motd.BanMessage.MessageTempBan");
	}
	
	public static String getFormatDate() {
		return main.getConfig().getString("Motd.BanMessage.FormatDate");
	}
	
	public static String getFormatTime() {
		return main.getConfig().getString("Motd.BanMessage.FormatTime");
	}
	
	public static boolean randomMotdEnabled() {
		return main.getConfig().getBoolean("Motd.RandomMessages.Enable");
	}
	
	public static boolean banMotdEnabled() {
		return main.getConfig().getBoolean("Motd.BanMessage.Enable");
	}
	
	public static boolean whitelistMotdEnabled() {
		return main.getConfig().getBoolean("Motd.WhitelistMessage.Enable");
	}
	
	public static boolean restrictedModeEnabled() {
		return main.getConfig().getBoolean("RestrictedMode.Enable");
	}
}
