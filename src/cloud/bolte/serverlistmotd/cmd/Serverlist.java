package cloud.bolte.serverlistmotd.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cloud.bolte.serverlistmotd.SpigotConfig;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class Serverlist implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("serverlist")) {
			switch(args.length) {
			case 0:
				sender.sendMessage("§8§l---------------------------------");
				sender.sendMessage("§e§lServerlist§6§lMOTD | §cFIRESTORM UPDATE");
				sender.sendMessage("§7   §o~by Strumswell");
				sender.sendMessage("§8§l---------------------------------");
				sender.sendMessage("§6§l> §eList of commands:");
				sender.sendMessage("  §e/serverlist §7§o* help screen");
				sender.sendMessage("  §e/serverlist reload §7§o* reload config");
				sender.sendMessage("  §e/serverlist restrictedmode");
				sender.sendMessage("  §e/serverlist versiontext");
				sender.sendMessage("  §e/serverlist randommotd");
				sender.sendMessage("  §e/serverlist banmotd");
				sender.sendMessage("  §e/serverlist whitelistmotd");
				sender.sendMessage("  §e/serverlist versiontext");
				return true;
			case 1:
				if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("serverlist.reload")) {
						SpigotConfig.reloadSmotdConfig();
						sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cConfig reloaded!");
					} else sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cYou don't have permissions!");
					return true;
				} else  if (args[0].equalsIgnoreCase("restrictedmode")) {
					return true;
					
				} else  if (args[0].equalsIgnoreCase("versiontext")) {
					return true;
					
				} else  if (args[0].equalsIgnoreCase("randommotd")) {
					return true;
					
				} else  if (args[0].equalsIgnoreCase("banmotd")) {
					return true;
					
				} else  if (args[0].equalsIgnoreCase("whitelistmotd")) {
					return true;
					
				} else  if (args[0].equalsIgnoreCase("versiontext")) {
					return true;
					
				} else return false;
			}
		}  
		return false;
	}
}
