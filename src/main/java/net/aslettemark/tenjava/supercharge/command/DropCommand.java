package net.aslettemark.tenjava.supercharge.command;

import net.aslettemark.tenjava.supercharge.SuperCharge;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DropCommand implements CommandExecutor {

    private SuperCharge plugin;

    public DropCommand(SuperCharge tj) {
        this.plugin = tj;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.log.info("The console can't drop items.");
            return true;
        }
        Player player = (Player) sender;
        if (plugin.droppers.contains(player)) {
            plugin.droppers.remove(player);
            player.sendMessage(ChatColor.AQUA + "You may no longer sacrifice your items.");
        } else {
            plugin.droppers.add(player);
            player.sendMessage(ChatColor.AQUA + "You may now sacrifice your items by dropping them.");
            player.sendMessage(ChatColor.RED + "WARNING" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Any blocks dropped while in drop mode will be sacrificed");
        }
        return true;
    }
}
