package com.stabilishednetwork.tatanpoker09.cob;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public void onEnable(){
	//		PluginManager pm = Bukkit.getPluginManager(); 

		getCommand("start").setExecutor(new StartCommand());
	}
	public void onDisable(){
		
	}
}
