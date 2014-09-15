package com.stabilishednetwork.tatanpoker09.cob.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

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
}
