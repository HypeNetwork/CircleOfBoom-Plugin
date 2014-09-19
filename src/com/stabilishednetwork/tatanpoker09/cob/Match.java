package com.stabilishednetwork.tatanpoker09.cob;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

import com.stabilishednetwork.tatanpoker09.cob.utils.BlockUtils;
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
	static long timeForEachCycle = 100L;
	private static boolean matchPlaying;
	private static int taskToCancel;

	public Match(Location center, Map map){
		this.setCenter(center);
		this.setMap(map);
		this.setMatch(this);
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
		File mapFolder = match.getMap().getMapFolder();
		File dest = null;
		try {
			dest = new File(Bukkit.getWorldContainer().getCanonicalPath(), mapFolder.getName());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		World worldToPlay;
		if(!dest.exists()){
			dest.mkdir();
			try {
				FileUtils.copyFolder(mapFolder, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
			worldToPlay = new WorldCreator(mapFolder.getName()).createWorld();
		} else {
			worldToPlay = Bukkit.getWorld(dest.getName());
		}
		Main.setCurrentWorld(worldToPlay);
		worldToPlay.setSpawnLocation(0, 51, 0);
		BlockUtils.setRadious((int) (BlockUtils.getLastBlockInCircle(Match.getMatch().getCenter()).getX()-2));
		for(Player player : Bukkit.getOnlinePlayers()){
			player.teleport(worldToPlay.getSpawnLocation());
			player.setHealth(20);
			player.setGameMode(GameMode.CREATIVE);
		}
	}

	public void loadLocations() {
		locations.clear();
		Location location = new Location(Main.getCurrentWorld(), 6, 53, -6);
		location.setYaw(45);
		locations.add(location);
		location = new Location(Main.getCurrentWorld(), 6, 53, 6);
		location.setYaw(135);
		locations.add(location);
		location = new Location(Main.getCurrentWorld(), -6, 53, 6);
		location.setYaw(-135);
		locations.add(location);
		location = new Location(Main.getCurrentWorld(), -6, 53, -6);
		location.setYaw(-45);
		locations.add(location);
		location = new Location(Main.getCurrentWorld(), 0, 53, -8);
		location.setYaw(0);
		locations.add(location);
		location = new Location(Main.getCurrentWorld(), 8, 53, 0);
		location.setYaw(90);
		locations.add(location);
		location = new Location(Main.getCurrentWorld(), 0, 53, 8);
		location.setYaw(180);
		locations.add(location);
		location = new Location(Main.getCurrentWorld(), -8, 53, 0);
		location.setYaw(-90);
		locations.add(location);
	}
	public static void startMatch(){
		matchPlaying = true;
		match.setState(MatchState.PLAYING);
		Bukkit.getScheduler().cancelTask(taskToCancel);
		taskToCancel = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("COB"), new Runnable() {
			int x = 0;
			int numberOfMaxTNTs = 4;
			@Override
			public void run() {
				if(matchPlaying){
					x++;
					if(x>3){
						timeForEachCycle = timeForEachCycle-(timeForEachCycle/10);
						numberOfMaxTNTs++;
						x = 0;
					}
					Random rn = new Random();
					int answer = rn.nextInt(numberOfMaxTNTs) + ((x+1)*2);
					for(int x = 0;x<answer;x++){
						Entity tnt = Main.getCurrentWorld().spawn(BlockUtils.getRandomLocationInCircle(), TNTPrimed.class);
						((TNTPrimed)tnt).setFuseTicks(50);
					}
				} else {
					match.setState(MatchState.ENDED);
					Bukkit.getScheduler().cancelTask(taskToCancel);
					Bukkit.broadcastMessage("Thanks for playing Circle Of Boom DEMO!");
				}
			}
		}, 0L, timeForEachCycle);
	}
	public static void endMatch(){
		matchPlaying = false;
	}
	public static void startMatchCountdown() {
		for(Player player : getMatch().getSurvivors()){
			player.setGameMode(GameMode.SURVIVAL);
			player.setFoodLevel(20);
		}
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