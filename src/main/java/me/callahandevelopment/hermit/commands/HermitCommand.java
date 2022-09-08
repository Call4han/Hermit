package me.callahandevelopment.hermit.commands;


import me.callahandevelopment.hermit.HermitMain;
import me.callahandevelopment.hermit.files.LangYaml;
import me.callahandevelopment.hermit.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;
import java.util.List;

public class HermitCommand implements TabExecutor {
    private static HermitMain plugin;
    public HermitCommand(HermitMain plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PluginDescriptionFile descriptionFile = plugin.getDescription();
        String version = descriptionFile.getVersion();

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                p.sendMessage(ChatColor.GREEN + "Plugin by Callahan!");
                p.sendMessage(ChatColor.GREEN + "Version: " + version);
                p.sendMessage(ChatColor.GREEN + descriptionFile.getDescription());
            }
            if (args.length==1){
                if (p.hasPermission("Hermit.op")&&(p.isOp())){
                    if (args[0].equalsIgnoreCase("open")){
                        InventoryUtil.OpenInventoryMain(p);
                    }else if (args[0].equalsIgnoreCase("reload")){
                        plugin.reloadConfig();
                        LangYaml.reload();
                        p.sendMessage(ChatColor.GREEN + "Hermit reloaded");
                    }
                }else {
                  p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("dont-permission")));
                }


            }

        }






        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==1){
            List<String> list = new ArrayList<>();
            list.add("open");
            list.add("reload");
            return list;
        }
        return null;
    }
}
