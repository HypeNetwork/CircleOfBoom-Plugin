package com.stabilishednetwork.tatanpoker09.cob.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.stabilishednetwork.tatanpoker09.cob.Match;
import com.stabilishednetwork.tatanpoker09.cob.MatchState;

public class StartCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(Match.getMatch().getState().equals(MatchState.PREMATCH)){
			for(Player player : Bukkit.getOnlinePlayers()){
				Match.getMatch().getSurvivors().add(player);
			}
			if(Match.getMatch().getSurvivors().size()<1){
				sender.sendMessage(ChatColor.RED+"You need at least 2 players to start the game.");
				sender.sendMessage(ChatColor.RED+"You currently have: "+Match.getMatch().getSurvivors().size()+" survivors");
			} else {
				Match.getMatch().loadLocations();
				Match.getMatch().teleportPlayers();
				Match.startMatchCountdown();
				return true;
			}
		}
		return false;
	}
}
