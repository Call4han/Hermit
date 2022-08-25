package me.callahandevelopment.hermit;

import me.callahandevelopment.hermit.commands.HermitCommand;
import me.callahandevelopment.hermit.listeners.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HermitMain extends JavaPlugin {
    public static HermitMain plugin;

    @Override
    public void onEnable() {
       plugin = this;
       this.getCommand("hermit").setExecutor(new HermitCommand(this));
       getServer().getPluginManager().registerEvents(new InventoryListener(this),this);
       getConfig().options().copyDefaults();
       saveDefaultConfig();

    }

    @Override
    public void onDisable() {

    }

    public static HermitMain getPlugin() {
        return plugin;
    }
}
