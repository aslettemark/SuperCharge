package net.aslettemark.tenjava.supercharge.command;

import net.aslettemark.tenjava.supercharge.SuperCharge;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListEffectsCommand implements CommandExecutor {

    private SuperCharge plugin;

    public ListEffectsCommand(SuperCharge tj) {
        this.plugin = tj;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String effects = ChatColor.AQUA + "Available effects: " + ChatColor.WHITE;
        for (String s : this.plugin.enabledEffects.keySet()) {
            effects = effects + s + ", ";
        }
        sender.sendMessage(effects.substring(0, effects.length() - 2));
        return true;
    }

}
