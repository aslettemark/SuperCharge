package com.tenjava.entries.aslettemark.t2.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tenjava.entries.aslettemark.t2.TenJava;

public class EffectCommand implements CommandExecutor {

    private TenJava plugin;

    public EffectCommand(TenJava tj) {
        this.plugin = tj;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Players only.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length < 2) {
            sender.sendMessage(ChatColor.GOLD + "Usage: /" + label + " <effect|remove> <info|on> [amount/time]");
            sender.sendMessage(ChatColor.AQUA + "Please note that any unused effects are removed on disconnect");
            sender.sendMessage(ChatColor.AQUA + "/listeffects To see a list of allowed effects");
            return true;
        }
        int amount = 1;
        String message = "";
        if (plugin.effectEnabled(args[0]) && (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("buy"))) {
            switch (args[0]) {
                case "alchemy":
                    amount = 1;
                    message = "Right click stone blocks with a stick to turn it into gold!";
                    break;
                case "lightning":
                    amount = 1;
                    message = "Right click with a stick to summon lightning. Use at own risk.";
                    break;
                default:
                    player.sendMessage(ChatColor.AQUA + "There was an error");
                    break;
            }
            if (args.length > 2) {
                amount = Integer.parseInt(args[2]);
            }
            if (plugin.energylevels.get(player) >= amount * (plugin.enabledEffects.get(args[0]))) {
                plugin.playerEffects.put(player, args[0]);
                plugin.usageLeft.put(player, amount);
                plugin.setEnergy(player, plugin.energylevels.get(player) - (plugin.enabledEffects.get(args[0])) * amount);
                player.sendMessage(ChatColor.AQUA + args[0] + " acquired for " + amount * (plugin.enabledEffects.get(args[0])) + " energy points.");
                player.sendMessage(ChatColor.AQUA + message);
                sender.sendMessage(ChatColor.AQUA + "Please note that any unused effects are removed on disconnect");
            } else {
                player.sendMessage(ChatColor.AQUA + "You don't have enough energy to do that!");
            }
        } else if (plugin.effectEnabled(args[0]) && args[1].equalsIgnoreCase("info")) {
            String m = "";
            switch (args[0]) {
                case "alchemy":
                    m = "Turns stone to gold. Cost: " + ChatColor.WHITE + plugin.enabledEffects.get(args[0]);
                    break;
                case "lightning":
                    m = "Makes you look like Thor. Possibly dangerous.";
                    break;
                default:
                    m = "Info not available.";
                    break;
            }
            player.sendMessage(ChatColor.AQUA + m);
        } else if (args[0].equalsIgnoreCase("remove")) {
            plugin.playerEffects.put(player, null);
            plugin.usageLeft.put(player, 0);
            player.sendMessage(ChatColor.GOLD + "All effects cleared.");
        } else {
            player.sendMessage(ChatColor.AQUA + "Effect unknown/not in use");
            sender.sendMessage(ChatColor.GOLD + "Usage: /" + label + " <effect|remove> <info|on> [amount/time]");
            sender.sendMessage(ChatColor.AQUA + "Please note that any unused effects are removed on disconnect");
            sender.sendMessage(ChatColor.AQUA + "/listeffects To see a list of allowed effects");
        }
        return true;
    }

}
