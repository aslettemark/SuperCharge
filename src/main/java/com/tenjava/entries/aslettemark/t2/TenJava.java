package com.tenjava.entries.aslettemark.t2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
    
    public Logger log = this.getLogger();
    public HashMap<Material, Integer> materials = new HashMap<Material, Integer>();
    public ArrayList<Player> droppers = new ArrayList<Player>();
    
    @Override
    public void onEnable() {
        log.info("Hi.");
        loadMaterials();
    }
    
    @Override
    public void onDisable() {
        
    }
    
    @EventHandler
    void onDrop(PlayerDropItemEvent event) {
        
    }
    
    private void loadMaterials() {
        materials.put(Material.COAL, 8);
        materials.put(Material.IRON_INGOT, 10);
        materials.put(Material.IRON_ORE, 8);
    }
    
}
