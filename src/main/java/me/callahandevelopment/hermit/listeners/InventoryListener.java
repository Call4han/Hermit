package me.callahandevelopment.hermit.listeners;

import me.callahandevelopment.hermit.HermitMain;
import me.callahandevelopment.hermit.files.LangYaml;
import me.callahandevelopment.hermit.tasks.ItemTask;
import me.callahandevelopment.hermit.utils.InventoryUtil;
import me.callahandevelopment.hermit.utils.ItemsUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class InventoryListener implements Listener {
    private static HermitMain plugin;
    public InventoryListener(HermitMain plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onClickInventory(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();


        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Inventory-main-title")))){
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
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("warning-messsage-air-click")));
            }



        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("inventory-create-title")))){
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
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("warning-messsage-air-click")));

            }
        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("inventory-edit-title")))){
            try {
                switch (e.getCurrentItem().getType()){
                    case PAPER:
                        e.setCancelled(true);
                        ItemsUtils.DeleteSkills(p);
                        p.closeInventory();
                        break;
                    case PRISMARINE_SHARD:
                        e.setCancelled(true);
                        p.closeInventory();
                        InventoryUtil.OpenInventoryMain(p);
                        break;
                    case BARRIER:
                        e.setCancelled(true);
                        p.closeInventory();
                        ItemsUtils.DeleteItem(p);
                        break;

                }
                e.setCancelled(true);
            }catch (NullPointerException exception){
               p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("warning-messsage-air-click")));
            }
        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Inventory-options-title")))){
            try {
                switch (e.getCurrentItem().getType()){
                    case PRISMARINE_SHARD:
                        e.setCancelled(true);
                        p.closeInventory();
                        InventoryUtil.CreateInventory(p);
                        break;
                    case WHITE_DYE:
                        e.setCancelled(true);
                        
                        ItemStack playeritem = p.getEquipment().getItemInMainHand();
                        ItemMeta playeritemeta =  playeritem.getItemMeta();
                        PersistentDataContainer dataitem = playeritemeta.getPersistentDataContainer();

                        if (dataitem.has(new NamespacedKey(HermitMain.getPlugin(),"message"),PersistentDataType.STRING)){
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skill-Lightning-Already")));

                        }else {
                            dataitem.set(new NamespacedKey(HermitMain.getPlugin(),"message"),PersistentDataType.STRING,"PRUEBA");
                            playeritem.setItemMeta(playeritemeta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Lightning-Done")));
                        }
                        break;

                    case WITHER_SKELETON_SKULL:
                        ItemStack playeritemhand = p.getEquipment().getItemInMainHand();
                        ItemMeta playermeta = playeritemhand.getItemMeta();
                        PersistentDataContainer dataitemskull = playermeta.getPersistentDataContainer();

                        if (dataitemskull.has(new NamespacedKey(HermitMain.getPlugin(),"skull"),PersistentDataType.STRING)){
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skill-Skull-Already")));


                        }else {
                            dataitemskull.set(new NamespacedKey(HermitMain.getPlugin(),"skull"),PersistentDataType.STRING,"skull");
                            playeritemhand.setItemMeta(playermeta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Skull-Done")));
                        }
                        break;
                    case BLACK_DYE:
                        ItemStack playerdye = p.getEquipment().getItemInMainHand();
                        ItemMeta playerdyeeta = playerdye.getItemMeta();
                        PersistentDataContainer datadye = playerdyeeta.getPersistentDataContainer();
                        if (datadye.has(new NamespacedKey(HermitMain.getPlugin(),"dye"),PersistentDataType.STRING)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skill-Dragon-Already")));
                        }else{
                            datadye.set(new NamespacedKey(HermitMain.getPlugin(),"dye"),PersistentDataType.STRING,"dye");
                            playerdye.setItemMeta(playerdyeeta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Dragon-Done")));
                        }
                        break;
                    case FIRE_CHARGE:
                        ItemStack playercharge = p.getEquipment().getItemInMainHand();
                        ItemMeta playerchargeeta = playercharge.getItemMeta();
                        PersistentDataContainer datacharge = playerchargeeta.getPersistentDataContainer();
                        if (datacharge.has(new NamespacedKey(HermitMain.getPlugin(),"fireball"),PersistentDataType.STRING)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skill-Fireball-Already")));
                        }else {
                            datacharge.set(new NamespacedKey(HermitMain.getPlugin(),"fireball"),PersistentDataType.STRING,"fireball");
                            playercharge.setItemMeta(playerchargeeta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Fireball-Done")));
                        }
                        break;
                    case ARROW:
                        ItemStack playerarrow = p.getEquipment().getItemInMainHand();
                        ItemMeta playerarroweta = playerarrow.getItemMeta();
                        PersistentDataContainer dataarrow = playerarroweta.getPersistentDataContainer();
                        if (dataarrow.has(new NamespacedKey(HermitMain.getPlugin(),"arrow"),PersistentDataType.STRING)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skill-Arrow-Already")));
                        }else {
                            dataarrow.set(new NamespacedKey(HermitMain.getPlugin(),"arrow"),PersistentDataType.STRING,"arrow");
                            playerarrow.setItemMeta(playerarroweta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Arrow-Done")));
                        }
                        break;
                    case TNT:
                        ItemStack playertnt = p.getEquipment().getItemInMainHand();
                        ItemMeta playertnteta = playertnt.getItemMeta();
                        PersistentDataContainer datatnt = playertnteta.getPersistentDataContainer();
                        if (datatnt.has(new NamespacedKey(HermitMain.getPlugin(),"tnt"),PersistentDataType.STRING)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skill-TNT-Already")));
                        }else {
                            datatnt.set(new NamespacedKey(HermitMain.getPlugin(),"tnt"),PersistentDataType.STRING,"tnt");
                            playertnt.setItemMeta(playertnteta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-TNT-Done")));
                        }
                        break;
                    case SPLASH_POTION:
                        ItemStack playersplash = p.getEquipment().getItemInMainHand();
                        ItemMeta playersplashmmeta = playersplash.getItemMeta();
                        PersistentDataContainer datasplash = playersplashmmeta.getPersistentDataContainer();
                        if (datasplash.has(new NamespacedKey(HermitMain.getPlugin(),"potion"),PersistentDataType.STRING)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skill-Potion-Already")));
                        }else {
                            datasplash.set(new NamespacedKey(HermitMain.getPlugin(),"potion"),PersistentDataType.STRING,"splash");
                            playersplash.setItemMeta(playersplashmmeta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Potion-Done")));
                        }
                        break;
                    case FIREWORK_ROCKET:
                        ItemStack playerfirework = p.getEquipment().getItemInMainHand();
                        ItemMeta playerfireworkmeta = playerfirework.getItemMeta();
                        PersistentDataContainer datafirework = playerfireworkmeta.getPersistentDataContainer();
                        if (datafirework.has(new NamespacedKey(HermitMain.getPlugin(),"firework"),PersistentDataType.STRING)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skill-Firework-Already")));
                        }else {
                            datafirework.set(new NamespacedKey(HermitMain.getPlugin(),"firework"),PersistentDataType.STRING,"firework");
                            playerfirework.setItemMeta(playerfireworkmeta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Firework-Done")));
                        }
                        break;
                    case TRIDENT:
                        ItemStack playertrident = p.getEquipment().getItemInMainHand();
                        ItemMeta playertridentmeta = playertrident.getItemMeta();
                        PersistentDataContainer datatrident = playertridentmeta.getPersistentDataContainer();
                        if (datatrident.has(new NamespacedKey(HermitMain.getPlugin(),"trident"),PersistentDataType.STRING)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skill-Trident-Already")));
                        }else {
                            datatrident.set(new NamespacedKey(HermitMain.getPlugin(),"trident"),PersistentDataType.STRING,"trident");
                            playertrident.setItemMeta(playertridentmeta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Trident-Done")));
                        }
                        break;
                    case SNOWBALL:
    
                        ItemStack playersnow = p.getEquipment().getItemInMainHand();
                        ItemMeta playersnowmeta = playersnow.getItemMeta();
                        PersistentDataContainer datasnow = playersnowmeta.getPersistentDataContainer();
                        if (datasnow.has(new NamespacedKey(HermitMain.getPlugin(),"snow"),PersistentDataType.STRING)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Skill-Snowball-Already")));
                        }else {
                            datasnow.set(new NamespacedKey(HermitMain.getPlugin(),"snow"),PersistentDataType.STRING,"snow");
                            playersnow.setItemMeta(playersnowmeta);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Skills-Snowball-Done")));
                        }
                        break;

                }
                e.setCancelled(true);

            }catch (NullPointerException exception){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("warning-messsage-air-click")));
            }

        }
    }
}
