package com.tenjava.entries.aslettemark.t2.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.tenjava.entries.aslettemark.t2.TenJava;

public class JoinQuitListener implements Listener {

    private TenJava plugin;

    public JoinQuitListener(TenJava tj) {
        this.plugin = tj;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.energylevels.put(player, 0); //TODO: Load from file
        plugin.playerEffects.put(player, null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.energylevels.remove(player);
        plugin.playerEffects.remove(player);
    }

}
