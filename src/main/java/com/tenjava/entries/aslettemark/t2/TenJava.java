package com.tenjava.entries.aslettemark.t2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.aslettemark.t2.command.DropCommand;
import com.tenjava.entries.aslettemark.t2.command.EffectCommand;
import com.tenjava.entries.aslettemark.t2.command.ListEffectsCommand;
import com.tenjava.entries.aslettemark.t2.event.DropListener;
import com.tenjava.entries.aslettemark.t2.event.EffectListener;
import com.tenjava.entries.aslettemark.t2.event.JoinQuitListener;

public class TenJava extends JavaPlugin {

    public Logger log = this.getLogger();
    public HashMap<Material, Integer> materials = new HashMap<Material, Integer>(); //material, energy gained
    public HashMap<Player, Integer> energylevels = new HashMap<Player, Integer>(); //player, energy balance
    public HashMap<Player, String> playerEffects = new HashMap<Player, String>(); //player, effectname
    public HashMap<String, Integer> enabledEffects = new HashMap<String, Integer>(); //effectname, cost
    public HashMap<Player, Integer> usageLeft = new HashMap<Player, Integer>(); //player, uses left
    public ArrayList<Player> droppers = new ArrayList<Player>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        log.info("Hi.");
        loadMaterials();
        this.getServer().getPluginManager().registerEvents(new DropListener(this), this);
        this.getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);
        this.getServer().getPluginManager().registerEvents(new EffectListener(this), this);
        this.getCommand("drop").setExecutor(new DropCommand(this));
        this.getCommand("effect").setExecutor(new EffectCommand(this));
        this.getCommand("listeffects").setExecutor(new ListEffectsCommand(this));

        String enabled = "Enabled effects: ";
        for (String s : this.getConfig().getStringList("effects")) {
            enabledEffects.put(s, this.getConfig().getInt("cost." + s));
            enabled = enabled + s + ", ";
        }
        log.info(enabled.substring(0, enabled.length() - 2));
    }

    @Override
    public void onDisable() {
        log.info("Goodbye.");
        this.saveConfig();
    }

    private void loadMaterials() {
        FileConfiguration c = this.getConfig();
        String mat = "materials.";
        materials.put(Material.COAL, c.getInt(mat + "coal"));
        materials.put(Material.IRON_INGOT, c.getInt(mat + "iron_ingot"));
        materials.put(Material.IRON_ORE, c.getInt(mat + "iron_ore"));
        materials.put(Material.GOLD_ORE, c.getInt(mat + "gold_ore"));
        materials.put(Material.GOLD_INGOT, c.getInt(mat + "gold_ingot"));
        materials.put(Material.GOLD_BLOCK, c.getInt(mat + "gold_block"));
        materials.put(Material.IRON_BLOCK, c.getInt(mat + "iron_block"));
        materials.put(Material.DIAMOND, c.getInt(mat + "diamond"));
        materials.put(Material.DIAMOND_BLOCK, c.getInt(mat + "diamond_block"));
        materials.put(Material.REDSTONE, c.getInt(mat + "redstone"));
        materials.put(Material.LAPIS_ORE, c.getInt(mat + "lapis_ore"));
        materials.put(Material.LAPIS_BLOCK, c.getInt(mat + "lapis_block"));
    }

    public boolean effectEnabled(String e) {
        return this.enabledEffects.containsKey(e);
    }

    public void setEnergy(Player player, int energy) {
        this.getConfig().set("players." + player.getName(), energy);
        this.energylevels.put(player, energy);
        this.saveConfig();
    }

}
