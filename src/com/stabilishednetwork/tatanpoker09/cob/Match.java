package com.stabilishednetwork.tatanpoker09.cob;

import org.bukkit.Bukkit;


public class Match {
	private static int countdown = 30;
	private static int taskToCancel;
	public static void startMatch(){

	}

	public static void startMatchCountdown() {
		taskToCancel = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("COB"), new Runnable(){			
			@Override
			public void run() {
				if(countdown!=0){

				} else {
					Match.startMatch();
					taskToCancel.

				}

			}
		}, 20L, 0L);
	}
}
