package com.stabilishednetwork.tatanpoker09.cob.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.stabilishednetwork.tatanpoker09.cob.Match;

public class Timer {
	private static int taskToCancel;
	private static int countdown;
	public static void startTimer(int countdown) {
		Bukkit.getScheduler().cancelTask(taskToCancel);
		Timer.countdown = countdown;
		taskToCancel = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("COB"), new Runnable() {

			@Override
			public void run() {
				if(Timer.countdown!=0){
					Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"Empezando la partida en... "+ChatColor.GREEN+ChatColor.BOLD+Timer.countdown);
					Timer.countdown--;
					//Main.getCurrentWorld().playEffect(Match.getMatch().getCenter(), Effect.STEP_SOUND, 10);
				} else {
					Match.startMatch();
					Bukkit.getScheduler().cancelTask(taskToCancel);
				}
			}
		}, 0L, 20L);
	}
}