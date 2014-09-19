package com.stabilishednetwork.tatanpoker09.cob.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockListener implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		event.setCancelled(true);
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		event.setCancelled(true);
	}
	@EventHandler
	public void onTntBreakBlocksEvent(EntityExplodeEvent event){
		event.blockList().clear();
	}
}
