package com.tenjava.entries.aslettemark.t2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.aslettemark.t2.command.DropCommand;
import com.tenjava.entries.aslettemark.t2.command.EffectCommand;
import com.tenjava.entries.aslettemark.t2.command.ListEffectsCommand;
import com.tenjava.entries.aslettemark.t2.event.DropListener;
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
        log.info("Hi.");
        loadMaterials();
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new DropListener(this), this);
        this.getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);
        this.getCommand("drop").setExecutor(new DropCommand(this));
        this.getCommand("effect").setExecutor(new EffectCommand(this));
        this.getCommand("listeffects").setExecutor(new ListEffectsCommand(this));

        String enabled = "Enabled effects: ";
        for (String s : this.getConfig().getStringList("effects")) {
            enabledEffects.put(s, 30); //TODO custom cost
            enabled = enabled + s + ", ";
        }
        log.info(enabled.substring(0, enabled.length() - 2));
    }

    @Override
    public void onDisable() {
        log.info("Goodbye.");
    }

    private void loadMaterials() {
        materials.put(Material.COAL, 8);
        materials.put(Material.IRON_INGOT, 10);
        materials.put(Material.IRON_ORE, 8);
    }

    public boolean effectEnabled(String e) {
        return this.enabledEffects.containsKey(e);
    }

}
