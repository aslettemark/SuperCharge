package net.aslettemark.tenjava.supercharge.event;

import net.aslettemark.tenjava.supercharge.SuperCharge;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EffectListener implements Listener {

    private SuperCharge plugin;

    public EffectListener(SuperCharge tj) {
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
                        this.updateUsage(player, 1);
                        return;
                    }
                }
            }
        }
        if (player.getItemInHand().getType() == Material.STICK) {
            if (plugin.playerEffects.get(player) != null) {
                if (plugin.playerEffects.get(player).equalsIgnoreCase("lightning")) {
                    int x = event.getPlayer().getLocation().getBlockX();
                    int y = event.getPlayer().getLocation().getBlockY();
                    int z = event.getPlayer().getLocation().getBlockZ();
                    World w = player.getWorld();

                    w.strikeLightning(new Location(w, x + 3, y, z));
                    w.strikeLightning(new Location(w, x + 3, y, z + 1));
                    w.strikeLightning(new Location(w, x + 3, y, z - 1));
                    w.strikeLightning(new Location(w, x - 3, y, z));
                    w.strikeLightning(new Location(w, x - 3, y, z + 1));
                    w.strikeLightning(new Location(w, x - 3, y, z - 1));
                    w.strikeLightning(new Location(w, x, y, z + 3));
                    w.strikeLightning(new Location(w, x + 1, y, z + 3));
                    w.strikeLightning(new Location(w, x - 1, y, z + 3));
                    w.strikeLightning(new Location(w, x, y, z - 3));
                    w.strikeLightning(new Location(w, x + 1, y, z - 3));
                    w.strikeLightning(new Location(w, x - 1, y, z - 3));
                    
                    this.updateUsage(player, 1);
                    return;
                }
            }
        }
        if (player.getItemInHand().getType() == Material.STICK) {
            if (plugin.playerEffects.get(player) != null) {
                if (plugin.playerEffects.get(player).equalsIgnoreCase("lightshow")) {
                    int x = event.getPlayer().getLocation().getBlockX();
                    int y = event.getPlayer().getLocation().getBlockY();
                    int z = event.getPlayer().getLocation().getBlockZ();
                    World w = player.getWorld();

                    w.strikeLightningEffect(new Location(w, x + 3, y, z));
                    w.strikeLightningEffect(new Location(w, x + 3, y, z + 1));
                    w.strikeLightningEffect(new Location(w, x + 3, y, z - 1));
                    w.strikeLightningEffect(new Location(w, x - 3, y, z));
                    w.strikeLightningEffect(new Location(w, x - 3, y, z + 1));
                    w.strikeLightningEffect(new Location(w, x - 3, y, z - 1));
                    w.strikeLightningEffect(new Location(w, x, y, z + 3));
                    w.strikeLightningEffect(new Location(w, x + 1, y, z + 3));
                    w.strikeLightningEffect(new Location(w, x - 1, y, z + 3));
                    w.strikeLightningEffect(new Location(w, x, y, z - 3));
                    w.strikeLightningEffect(new Location(w, x + 1, y, z - 3));
                    w.strikeLightningEffect(new Location(w, x - 1, y, z - 3));
                    
                    this.updateUsage(player, 1);
                    return;
                }
            }
        }
        if (player.getItemInHand().getType() == Material.COMPASS && event.hasBlock()) {
            if (plugin.playerEffects.get(player) != null) {
                if (plugin.playerEffects.get(player).equalsIgnoreCase("bedteleport")) {
                    Location bedLoc = event.getPlayer().getBedSpawnLocation();
                    if(bedLoc != null) {
                        event.getPlayer().teleport(bedLoc);
                        player.sendMessage(ChatColor.GOLD + "Teleported to bed");
                        this.updateUsage(player, 1);
                        return;
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have a bed.");
                        return;
                    }
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
