package com.stabilishednetwork.tatanpoker09.cob;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(Bukkit.getOnlinePlayers().length<2){
			sender.sendMessage(ChatColor.RED+"You need at least 2 players to start the game.");
		}else {
			Match.startMatchCountdown();
			return true;
		}
		return false;
	}
}
