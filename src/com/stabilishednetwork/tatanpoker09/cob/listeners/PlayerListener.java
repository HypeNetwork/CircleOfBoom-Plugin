package com.stabilishednetwork.tatanpoker09.cob.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.stabilishednetwork.tatanpoker09.cob.Match;

public class PlayerListener implements Listener{
	int minPlayers = 10;
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(){
		if(Bukkit.getOnlinePlayers().length>minPlayers){
			Match.startMatchCountdown();
		}
	}
}
