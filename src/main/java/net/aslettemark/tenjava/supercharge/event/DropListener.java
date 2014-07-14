package net.aslettemark.tenjava.supercharge.event;

import net.aslettemark.tenjava.supercharge.SuperCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {

    private SuperCharge plugin;

    public DropListener(SuperCharge tj) {
        this.plugin = tj;
    }

    @EventHandler
    void onDrop(PlayerDropItemEvent event) {
        if (plugin.droppers.contains(event.getPlayer())) {
            if (plugin.materials.containsKey(event.getItemDrop().getItemStack().getType())) {
                int newlevel = plugin.energylevels.get(event.getPlayer()) + plugin.materials.get(event.getItemDrop().getItemStack().getType());
                plugin.energylevels.put(event.getPlayer(), newlevel);
                event.getItemDrop().remove();
                event.getPlayer().sendMessage("Your new energy level is: " + newlevel);
                this.plugin.setEnergy(event.getPlayer(), newlevel);
            }
        }
    }

}
