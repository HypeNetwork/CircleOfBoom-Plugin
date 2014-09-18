package com.stabilishednetwork.tatanpoker09.cob.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.stabilishednetwork.tatanpoker09.cob.Match;

public class PlayerListener implements Listener{
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Match.removeSurvivor(event.getEntity().getPlayer());
		if(Match.getSurvivors().size()==1){
			Match.setWinner(Match.getSurvivors().get(0));
		} else if(Match.getSurvivors().size()==0){
			Match.endMatch();
		}
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(Match.getSurvivors().contains(player)){
			Match.getSurvivors().remove(player);
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		Match.getSurvivors().add(player);
	}
}
