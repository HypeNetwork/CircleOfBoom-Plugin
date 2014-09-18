package com.stabilishednetwork.tatanpoker09.cob;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.stabilishednetwork.tatanpoker09.cob.utils.Timer;

public class Match {
	private static List<Player> survivors = new ArrayList<Player>();
	private static Player winner;
	private static List<Location> locations = new ArrayList<Location>();
	private static MatchState state = MatchState.PREMATCH;

	public static void startMatch(){
		
	}
	public static void endMatch(){
		
	}
	public static void startMatchCountdown() {
		Timer.startTimer(10);
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
	public static List<Location> getLocations() {
		return locations;
	}
	public static MatchState getState() {
		return state;
	}
	public static void setState(MatchState state) {
		Match.state = state;
	}
	public static void teleportPlayers() {
		for(Player player : getSurvivors()){
			Random random = new Random();
			Location location = getLocations().get(random.nextInt(getLocations().size()));
			player.teleport(location);
			getLocations().remove(location);
		}	
	}
	public static void loadLocations() {
		locations.clear();
		Location location = new Location(Bukkit.getWorlds().get(0), 6, 53, -6);
		location.setYaw(45);
		locations.add(location);
		location = new Location(Bukkit.getWorlds().get(0), 6, 53, 6);
		location.setYaw(135);
		locations.add(location);
		location = new Location(Bukkit.getWorlds().get(0), -6, 53, 6);
		location.setYaw(-135);
		locations.add(location);
		location = new Location(Bukkit.getWorlds().get(0), -6, 53, -6);
		location.setYaw(-45);
		locations.add(location);
		location = new Location(Bukkit.getWorlds().get(0), 0, 53, -8);
		location.setYaw(0);
		locations.add(location);
		location = new Location(Bukkit.getWorlds().get(0), 8, 53, 0);
		location.setYaw(90);
		locations.add(location);
		location = new Location(Bukkit.getWorlds().get(0), 0, 53, 8);
		location.setYaw(180);
		locations.add(location);
		location = new Location(Bukkit.getWorlds().get(0), -8, 53, 0);
		location.setYaw(-90);
		locations.add(location);
	}
}