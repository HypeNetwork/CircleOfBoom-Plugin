package com.stabilishednetwork.tatanpoker09.cob.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.stabilishednetwork.tatanpoker09.cob.Main;
import com.stabilishednetwork.tatanpoker09.cob.Match;

public class PlayerListener implements Listener{
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Match.getMatch().removeSurvivor(event.getEntity().getPlayer());
		if(Match.getMatch().getSurvivors().size()==1){
			Match.getMatch().setWinner(Match.getMatch().getSurvivors().get(0));
		} else if(Match.getMatch().getSurvivors().size()==0){
			Match.endMatch();
		}
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(Match.getMatch().getSurvivors().contains(player)){
			Match.getMatch().getSurvivors().remove(player);
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		Match.getMatch().getSurvivors().add(player);
		player.teleport(Main.getCurrentWorld().getSpawnLocation());
	}
}
