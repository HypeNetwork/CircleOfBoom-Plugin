package com.stabilishednetwork.tatanpoker09.cob;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import com.stabilishednetwork.tatanpoker09.cob.utils.FileUtils;
import com.stabilishednetwork.tatanpoker09.cob.utils.Timer;

public class Match {
	private List<Player> survivors = new ArrayList<Player>();
	private Player winner;
	private List<Location> locations = new ArrayList<Location>();
	private MatchState state = MatchState.PREMATCH;
	private Location center;
	private static Match match;
	private Map map;

	public Match(Location center, Map map){
		this.setCenter(center);
		this.setMap(map);
	}
	
	
	public Location getCenter() {
		return center;
	}
	public static Match getMatch(){
		return match;
	}
	
	public List<Player> getSurvivors(){
		return survivors;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner){
		this.winner = winner;
	}
	public void removeSurvivor(Player playerToRemove){
		survivors.remove(playerToRemove);
	}
	public List<Location> getLocations() {
		return locations;
	}
	public MatchState getState() {
		return state;
	}
	public void setState(MatchState state) {
		this.state = state;
	}
	public void teleportPlayers() {
		for(Player player : getMatch().getSurvivors()){
			Random random = new Random();
			Location location = getMatch().getLocations().get(random.nextInt(getMatch().getLocations().size()));
			player.teleport(location);
			getMatch().getLocations().remove(location);
		}	
	}
	
	@SuppressWarnings("deprecation")
	public static void loadMatch(Match match){
		String mapFolderName = match.getMap().getName();
		File dest = new File(Bukkit.getWorldContainer().getAbsolutePath()+mapFolderName);
		if(!dest.exists()){
			dest.mkdir();
		}
		try {
			FileUtils.copyFolder(match.getMap().getMapFolder(), dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		World worldToPlay = new WorldCreator(mapFolderName).createWorld();
		Main.setCurrentWorld(worldToPlay);
		worldToPlay.setSpawnLocation(0, 51, 0);
		for(Player player : Bukkit.getOnlinePlayers()){
			player.teleport(worldToPlay.getSpawnLocation());
		}
	}
	
	public void loadLocations() {
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
	public static void startMatch(){
		
	}
	public static void endMatch(){
		
	}
	public static void startMatchCountdown() {
		Timer.startTimer(10);
	}
	public void setMatch(Match match) {
		Match.match = match;
	}
	public void setCenter(Location location) {
		this.center = location;
	}


	public Map getMap() {
		return map;
	}


	public void setMap(Map map) {
		this.map = map;
	}
}