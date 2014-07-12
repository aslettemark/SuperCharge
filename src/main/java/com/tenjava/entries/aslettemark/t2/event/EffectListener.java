package com.tenjava.entries.aslettemark.t2.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.tenjava.entries.aslettemark.t2.TenJava;

public class EffectListener implements Listener {
    
    private TenJava plugin;
    
    public EffectListener(TenJava tj) {
        this.plugin = tj;
    }
    
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.getItemInHand().getType() == Material.STICK && event.hasBlock()) {
            if(event.getClickedBlock().getType() == Material.STONE) {
                if(plugin.playerEffects.get(player).equalsIgnoreCase("alchemy")) {
                    event.getClickedBlock().setType(Material.GOLD_BLOCK);
                }
            }
        }
    }

}
