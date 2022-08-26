package me.callahandevelopment.hermit.commands;


import me.callahandevelopment.hermit.HermitMain;
import me.callahandevelopment.hermit.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class HermitCommand implements CommandExecutor {
    private static HermitMain plugin;
    public HermitCommand(HermitMain plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length==1){
                if (p.hasPermission("Hermit.open")&&(p.isOp())){
                    if (args[0].equalsIgnoreCase("open")){
                        InventoryUtil.OpenInventoryMain(p);
                    }
                }else {
                    p.sendMessage(ChatColor.RED + "You don´t permission for this");
                }


            }

        }






        return true;
    }
}
