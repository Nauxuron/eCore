package com.mcdr.ecore.listener;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

/*import java.util.List;
import org.bukkit.entity.EntityType;
import com.mcdr.corruption.CorruptionAPI;
import com.mcdr.ecore.GlobalDataManager;
import com.mcdr.ecore.eCoreStage;*/

import com.mcdr.ecore.eCore;
import com.mcdr.ecore.eLogger;

public class eCoreRedstoneListener implements Listener {
	private int[] rsLoc = {-37, 36, 35};
	private ArrayList<Location> signLocations = new ArrayList<Location>();
	
	public eCoreRedstoneListener(){
		signLocations.add(new Location(eCore.getWorld(), -36D, 40D, 34D));
		signLocations.add(new Location(eCore.getWorld(), -36D, 40D, 36D));
		signLocations.add(new Location(eCore.getWorld(), -38D, 40D, 36D));
	}
	
	@EventHandler
	public void onRedstoneUpdate(BlockRedstoneEvent e){	
		if(e.getBlock().getWorld().getName().equalsIgnoreCase(eCore.worldName)){
			Location uLoc = e.getBlock().getLocation();
			if(uLoc.getBlockX() == rsLoc[0] && uLoc.getBlockY() == rsLoc[1] && uLoc.getBlockZ() == rsLoc[2]){
				if (e.getOldCurrent()==0 && e.getNewCurrent()>0){
					for(Location loc: signLocations){
						loc.setWorld(e.getBlock().getWorld());
						Block b = loc.getBlock();
						try{
							Sign s = (Sign) b.getState();
							s.setLine(3, ChatColor.DARK_RED+" Denied!");
							s.update();
						} catch (ClassCastException err){
							eLogger.i("Targeted block ("+b.getType()+") is not a sign.");
						}
					}
				} else if (e.getOldCurrent()>0 && e.getNewCurrent()==0){
					for(Location loc: signLocations){
						loc.setWorld(e.getBlock().getWorld());
						Block b = loc.getBlock();
						try{
							Sign s = (Sign) b.getState();
							s.setLine(3, ChatColor.DARK_GREEN+" Granted!");
							s.update();
						} catch (ClassCastException err){
							eLogger.i("Targeted block ("+b.getType()+") is not a sign.");
						}
					}
				}
			}
		}
	}
}
