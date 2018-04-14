package cloud.bolte.serverlistmotd.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cloud.bolte.serverlistmotd.SpigotConfig;

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
				return true;
			case 1:
				if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("serverlist.reload")) {
						SpigotConfig.reloadSmotdConfig();
						sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cConfig reloaded!");
					} else sender.sendMessage("§e§oServerlist§6§lMOTD §7> §cYou don't have permissions!");
					return true;
				} else return false;
			}
		}  
		return false;
	}
}
