package com.stabilishednetwork.tatanpoker09.cob;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.stabilishednetwork.tatanpoker09.cob.commands.StartCommand;
import com.stabilishednetwork.tatanpoker09.cob.listeners.PlayerListener;

public class Main extends JavaPlugin{
	public void onEnable(){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		getCommand("start").setExecutor(new StartCommand());
	}
	public void onDisable(){
		
	}
}
