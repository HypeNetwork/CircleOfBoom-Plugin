package com.stabilishednetwork.tatanpoker09.cob;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Match {
	private static List<Player> survivors = new ArrayList<Player>();
	private static Player winner;
	private static Location[] locations;
	@SuppressWarnings("deprecation")
	public static void startMatch(){
		for(Player player : Bukkit.getOnlinePlayers()){
			survivors.add(player);
		}
	}
	public static void endMatch(){
		
	}
	public static void startMatchCountdown() {
		
	}
	
	public static List<Player> getSurvivors(){
		return survivors;
	}
	public static Player getWinner() {
		return winner;
	}
	public static void setWinner(Player winner){
		Match.winner = winner;
	}
	public static void removeSurvivor(Player playerToRemove){
		survivors.remove(playerToRemove);
	}
	public static Location[] getLocations() {
		return locations;
	}
}