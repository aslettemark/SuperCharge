package net.aslettemark.tenjava.supercharge.event;

import net.aslettemark.tenjava.supercharge.SuperCharge;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private SuperCharge plugin;

    public JoinQuitListener(SuperCharge tj) {
        this.plugin = tj;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.energylevels.put(player, plugin.getConfig().getInt("players." + player.getName()));
        plugin.playerEffects.put(player, null);
        plugin.droppers.remove(player);
        plugin.usageLeft.put(player, 0);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.energylevels.remove(player);
        plugin.playerEffects.remove(player);
    }

}
