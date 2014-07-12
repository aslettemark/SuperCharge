package com.tenjava.entries.aslettemark.t2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.aslettemark.t2.command.DropCommand;
import com.tenjava.entries.aslettemark.t2.event.DropListener;
import com.tenjava.entries.aslettemark.t2.event.JoinQuitListener;

public class TenJava extends JavaPlugin {
    
    public Logger log = this.getLogger();
    public HashMap<Material, Integer> materials = new HashMap<Material, Integer>();
    public HashMap<Player, Integer> energylevels = new HashMap<Player, Integer>();
    public ArrayList<Player> droppers = new ArrayList<Player>();
    
    @Override
    public void onEnable() {
        log.info("Hi.");
        loadMaterials();
        this.getServer().getPluginManager().registerEvents(new DropListener(this), this);
        this.getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);
        this.getCommand("drop").setExecutor(new DropCommand(this));
    }
    
    @Override
    public void onDisable() {
        
    }
    
    private void loadMaterials() {
        materials.put(Material.COAL, 8);
        materials.put(Material.IRON_INGOT, 10);
        materials.put(Material.IRON_ORE, 8);
    }
    
}
