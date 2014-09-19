package com.stabilishednetwork.tatanpoker09.cob.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.stabilishednetwork.tatanpoker09.cob.Main;
import com.stabilishednetwork.tatanpoker09.cob.Match;
import com.stabilishednetwork.tatanpoker09.cob.utils.FileUtils;

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
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event){
		event.setRespawnLocation(Main.getCurrentWorld().getSpawnLocation());
	}
	
	@EventHandler
	public void serverCommandEvent(ServerCommandEvent event){
		if(event.getCommand().equalsIgnoreCase("stop")){
			for(World world : Bukkit.getWorlds()){
				if(world.equals(Bukkit.getWorlds().get(0)) || world.getName().endsWith("_nether") || world.getName().endsWith("the_end") ){

				} else {
					Bukkit.unloadWorld(world, false);
					FileUtils.delete(new File(world.getName()));
				}
			}
		}
	}
	@EventHandler
	public void playerCommandEvent(PlayerCommandPreprocessEvent event){
		if(event.getMessage().equalsIgnoreCase("/stop")){
			for(World world : Bukkit.getWorlds()){
				if(world.equals(Bukkit.getWorlds().get(0)) || world.getName().endsWith("_nether") || world.getName().endsWith("the_end") ){

				} else {
					Bukkit.unloadWorld(world, false);
					FileUtils.delete(new File(world.getName()));
				}
			}
		}
	}
}
