package com.stabilishednetwork.tatanpoker09.cob;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.stabilishednetwork.tatanpoker09.cob.commands.StartCommand;
import com.stabilishednetwork.tatanpoker09.cob.listeners.PlayerListener;
import com.stabilishednetwork.tatanpoker09.cob.utils.ScoreboardUtils;

public class Main extends JavaPlugin{
	private static final File mapsDirectory = new File("COBMaps/");
	private static World currentWorld;
	public void onEnable(){
		Map.loadMaps();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		getCommand("start").setExecutor(new StartCommand());
		loadMap(Map.getMaps().get(0));
	}
	public void onDisable(){
		
	}
	public static void loadMap(Map map){
		Map mapToLoad = Map.getMaps().get(0);
		Location center = ScoreboardUtils.getLocation(mapToLoad.getYml().getString("center"));
		Match.loadMatch(new Match(center, map));
	}
	public static File getMapsDirectory() {
		return mapsDirectory ;
	}
	public static World getCurrentWorld() {
		return currentWorld;
	}
	public static void setCurrentWorld(World worldToPlay) {
		currentWorld = worldToPlay;
		
	}
}
