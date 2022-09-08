package me.callahandevelopment.hermit.utils;

import me.callahandevelopment.hermit.HermitMain;
import me.callahandevelopment.hermit.files.LangYaml;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemsUtils {
    private HermitMain plugin;
    public ItemsUtils(HermitMain plugin){
        this.plugin = plugin;
    }
    public static void DeleteSkills(Player p){
        ItemStack itemInMainHand = p.getInventory().getItemInMainHand();
        ItemMeta meta = itemInMainHand.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        //AREGLAR LA MIERDA QE NO SE QITA LOS KEYS XD



        if (data.has(new NamespacedKey(HermitMain.getPlugin(),"message"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"message"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Lightning-Remove")));
        }

        if (data.has(new NamespacedKey(HermitMain.getPlugin(),"skull"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"skull"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Skull-Remove")));

        }
        if (data.has(new NamespacedKey(HermitMain.getPlugin(),"dye"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"dye"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Dragon-Remove")));
        }
        if (data.has(new NamespacedKey(HermitMain.getPlugin(),"fireball"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"fireball"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Fireball-Remove")));
        }
        if (data.has(new NamespacedKey(HermitMain.getPlugin(),"arrow"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"arrow"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Arrow-Remove")));
        }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"tnt"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"tnt"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-TNT-Remove")));
        }

        if (data.has(new NamespacedKey(HermitMain.getPlugin(),"potion"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"potion"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Potion-Remove")));
        }
        if (data.has(new NamespacedKey(HermitMain.getPlugin(),"firework"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"firework"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Firework-Remove")));
        }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"trident"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"trident"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Trident-Remove")));
        }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"snow"), PersistentDataType.STRING)){
            data.remove(new NamespacedKey(HermitMain.getPlugin(),"snow"));
            itemInMainHand.setItemMeta(meta);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-Snowball-Remove")));



        }else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skills-All-Remove")));
        }


    }
    public static void DeleteItem(Player p ){
        p.getEquipment().setItemInMainHand(null);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Item-Remove")));



    }
}
