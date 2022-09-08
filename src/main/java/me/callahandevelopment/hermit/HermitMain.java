package me.callahandevelopment.hermit;

import me.callahandevelopment.hermit.commands.HermitCommand;
import me.callahandevelopment.hermit.files.LangYaml;
import me.callahandevelopment.hermit.listeners.InventoryListener;
import me.callahandevelopment.hermit.listeners.ItemListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class HermitMain extends JavaPlugin {
    public static HermitMain plugin;
    private ArrayList<String> players = new ArrayList<>();

    @Override
    public void onEnable() {
       plugin = this;

       this.getCommand("hermit").setExecutor(new HermitCommand(this));
       getServer().getPluginManager().registerEvents(new InventoryListener(this),this);
      getServer().getPluginManager().registerEvents(new ItemListener(this),this);
       getConfig().options().copyDefaults();
       saveDefaultConfig();
        plugin.saveResource("lang.yml",false);
        LangYaml.setUp();
        LangYaml.save();

    }

    @Override
    public void onDisable() {

    }

    public static HermitMain getPlugin() {
        return plugin;
    }


    public void addPlayer(String  player){
        this.players.add(player);
    }
    public void removePlayer(String player){
        for (int i=0;i<players.size();i++){
            if (players.get(i).equals(player)){
                players.remove(i);
            }
        }
    }
    public Boolean containsObject(String player){
        for (int i=0;i<players.size();i++){
            if (players.get(i).equals(player)){
                return true;
            }

        }
        return false;

    }

}
