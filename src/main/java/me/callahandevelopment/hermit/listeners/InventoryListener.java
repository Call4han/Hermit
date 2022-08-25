package me.callahandevelopment.hermit.listeners;

import me.callahandevelopment.hermit.HermitMain;
import me.callahandevelopment.hermit.utils.InventoryUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryListener implements Listener {
    private static HermitMain plugin;
    public InventoryListener(HermitMain plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onClickInventory(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        String titlemain = e.getView().getTitle();
        String configtitlemain = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Inventory-main-title"));

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',HermitMain.getPlugin().getConfig().getString("Inventory-main-title")))){
            try {
                switch (e.getCurrentItem().getType()){
                    case GREEN_WOOL:
                        p.closeInventory();
                        e.setCancelled(true);
                        InventoryUtil.CreateInventory(p);
                        break;
                    case NAME_TAG:
                        p.closeInventory();
                        e.setCancelled(true);
                        InventoryUtil.EditInventory(p);
                        break;
                    case RED_WOOL:
                        p.closeInventory();
                        e.setCancelled(true);
                        break;


                }
                e.setCancelled(true);
            }catch (NullPointerException exception){
                p.sendMessage("Only click items, don´t air");
            }



        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',HermitMain.getPlugin().getConfig().getString("inventory-create-title")))){
            try {
                switch (e.getCurrentItem().getType()){
                    case NETHER_STAR:
                        e.setCancelled(true);
                        p.closeInventory();
                        InventoryUtil.OptionInventory(p);
                        break;
                    case PRISMARINE_SHARD:
                        e.setCancelled(true);
                        p.closeInventory();
                        InventoryUtil.OpenInventoryMain(p);
                        break;

                }
                e.setCancelled(true);
            }catch (NullPointerException exception){
                p.sendMessage(ChatColor.RED + "Only click items, don´t air");

            }
        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',HermitMain.getPlugin().getConfig().getString("inventory-edit-title")))){
            try {
                switch (e.getCurrentItem().getType()){
                    case BARRIER:
                        e.setCancelled(true);
                        p.closeInventory();
                        break;
                    case PRISMARINE_SHARD:
                        e.setCancelled(true);
                        p.closeInventory();
                        InventoryUtil.OpenInventoryMain(p);
                        break;

                }
                e.setCancelled(true);
            }catch (NullPointerException exception){
                p.sendMessage(ChatColor.GRAY + "Only click items, don´t air");
            }
        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',HermitMain.getPlugin().getConfig().getString("Inventory-options-title")))){
            try {
                switch (e.getCurrentItem().getType()){
                    case PRISMARINE_SHARD:
                        e.setCancelled(true);
                        p.closeInventory();
                        InventoryUtil.CreateInventory(p);
                        break;

                }
                e.setCancelled(true);
            }catch (NullPointerException exception){
                p.sendMessage(ChatColor.GRAY + "Only click items, don´t air");
            }

        }
    }
}
