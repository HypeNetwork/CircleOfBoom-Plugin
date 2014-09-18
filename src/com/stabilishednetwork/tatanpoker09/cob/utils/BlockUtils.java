package com.stabilishednetwork.tatanpoker09.cob.utils;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.stabilishednetwork.tatanpoker09.cob.Match;

public class BlockUtils {
	public static Location getLastBlockInCircle(Location center){
		//Location center = Match.getMatch().getCenter();
		while(true){
			center.setX(center.getX()+1);
			int layersWithoutBlocks = 0;
			for(int y=0;y<11;y++){
				Location blockLocationTop = new Location(Bukkit.getWorlds().get(0), center.getX(), center.getY()+y, center.getZ());
				Location blockLocationBottom = new Location(Bukkit.getWorlds().get(0), center.getX(), center.getY()-y, center.getZ());
				if(blockLocationTop.getBlock().getType().equals(Material.AIR) && blockLocationBottom.getBlock().getType().equals(Material.AIR)){
					layersWithoutBlocks++;
				} else {
					break;
				}
			}
			if(layersWithoutBlocks==11){
				break;
			}
		}
		center.setX(center.getX());
		return center;
	}
	public static double getRadious(){
		return getLastBlockInCircle(Match.getMatch().getCenter()).getX()-2;
	}
	public static Block getRandomBlockInCircle(){
		Random rndGen = new Random();
		int radious = (int)getRadious();
		int r = rndGen.nextInt(radious-2);
		int x = rndGen.nextInt(radious);
		double z = Math.sqrt(Math.pow(r,2) - Math.pow(x,2));
		if(rndGen.nextBoolean()) x *= -1;
		if(rndGen.nextBoolean()) z *= -1;
		return new Location(Bukkit.getWorlds().get(0), x, Bukkit.getWorlds().get(0).getHighestBlockYAt(x, (int)z), z).getBlock();
	}
}
