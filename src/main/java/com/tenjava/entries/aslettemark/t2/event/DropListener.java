package com.tenjava.entries.aslettemark.t2.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.tenjava.entries.aslettemark.t2.TenJava;

public class DropListener implements Listener {
    
    private TenJava plugin;
    
    public DropListener(TenJava tj) {
        this.plugin = tj;
    }
    
    @EventHandler
    void onDrop(PlayerDropItemEvent event) {
        if(plugin.droppers.contains(event.getPlayer())) {
            if(plugin.materials.containsKey(event.getItemDrop().getItemStack().getType())) {
                int newlevel = plugin.energylevels.get(event.getPlayer()) + plugin.materials.get(event.getItemDrop().getItemStack().getType());
                plugin.energylevels.put(event.getPlayer(), newlevel);
                event.getPlayer().sendMessage("Your new energy level is: " + newlevel);
            }
        }
    }
    
}
