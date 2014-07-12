package com.tenjava.entries.aslettemark.t2.event;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
        if (player.getItemInHand().getType() == Material.STICK && event.hasBlock()) {
            if (event.getClickedBlock().getType() == Material.STONE) {
                if (plugin.playerEffects.get(player) != null) {
                    if (plugin.playerEffects.get(player).equalsIgnoreCase("alchemy")) {
                        event.getClickedBlock().setType(Material.GOLD_BLOCK);
                        plugin.usageLeft.put(player, plugin.usageLeft.get(player) - 1);
                        this.updateUsage(player, 1);
                        return;
                    }
                }
            }
        }
        if (player.getItemInHand().getType() == Material.STICK) {
            if(plugin.playerEffects.get(player) != null) {
                if (plugin.playerEffects.get(player).equalsIgnoreCase("lightning")) {
                    int x = event.getPlayer().getLocation().getBlockX();
                    int y = event.getPlayer().getLocation().getBlockY();
                    int z = event.getPlayer().getLocation().getBlockZ();
                    World w = player.getWorld();
                    
                    w.strikeLightning(new Location(w, x+3, y, z));
                    w.strikeLightning(new Location(w, x+3, y+1, z));
                    w.strikeLightning(new Location(w, x+3, y-1, z));
                    w.strikeLightning(new Location(w, x-3, y, z));
                    w.strikeLightning(new Location(w, x-3, y+1, z));
                    w.strikeLightning(new Location(w, x-3, y-1, z));
                    w.strikeLightning(new Location(w, x, y+3, z));
                    w.strikeLightning(new Location(w, x+1, y+3, z));
                    w.strikeLightning(new Location(w, x-1, y+3, z));
                    w.strikeLightning(new Location(w, x, y-3, z));
                    w.strikeLightning(new Location(w, x+1, y-3, z));
                    w.strikeLightning(new Location(w, x-2, y-3, z));
                    
                    this.updateUsage(player, 1);
                }
            }
        }
    }
    
    private void updateUsage(Player player, int use) {
        plugin.usageLeft.put(player, plugin.usageLeft.get(player) - use);
        if (plugin.usageLeft.get(player) == 0) {
            plugin.playerEffects.put(player, null);
            player.sendMessage(ChatColor.RED + "You're out of uses.");
            return;
        }
        player.sendMessage(ChatColor.GOLD + "Uses left: " + ChatColor.WHITE + plugin.usageLeft.get(player));
        return;
    }
}
