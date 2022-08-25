package me.callahandevelopment.hermit.utils;


import me.callahandevelopment.hermit.HermitMain;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InventoryUtil {
    private static HermitMain plugin;

    public InventoryUtil(HermitMain plugin) {
        this.plugin = plugin;
    }

    public static void OpenInventoryMain(Player p){
        //Inventory main = Bukkit.createInventory(null,9, ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Inventory-main-title")));
        Inventory main = Bukkit.createInventory(null,9,ChatColor.translateAlternateColorCodes('&',HermitMain.getPlugin().getConfig().getString("Inventory-main-title")));
        //CREATE
        ItemStack create = new ItemStack(Material.GREEN_WOOL,1);
        ItemMeta createmeta = create.getItemMeta();
        createmeta.setDisplayName(ChatColor.BLUE + "Create the item in your hand");
        List<String> lorecreate = new ArrayList<>();
        lorecreate.add("Create one item");
        lorecreate.add("In your hand");
        createmeta.setLore(lorecreate);
        create.setItemMeta(createmeta);
        //EDIT
        ItemStack edit = new ItemStack(Material.NAME_TAG,1);
        ItemMeta editmeta = edit.getItemMeta();
        editmeta.setDisplayName("Edit Your item in main hand");
        List<String> loreedit = new ArrayList<>();
        loreedit.add("Edit the item");
        loreedit.add("Of your main hand");
        editmeta.setLore(loreedit);
        edit.setItemMeta(editmeta);
        //LEAVE
        ItemStack leave = new ItemStack(Material.RED_WOOL,1);
        ItemMeta leavemeta = leave.getItemMeta();
        leavemeta.setDisplayName("Leave of gui");
        List<String> loreleave = new ArrayList<>();
        loreleave.add("Leaves");
        loreleave.add("Of gui");
        leavemeta.setLore(loreleave);
        leave.setItemMeta(leavemeta);
        main.setItem(0,create);
        main.setItem(1,edit);
        main.setItem(8,leave);
        p.openInventory(main);




    }
    public static void CreateInventory(Player p){

        Inventory create = Bukkit.createInventory(null,9,ChatColor.translateAlternateColorCodes('&',HermitMain.getPlugin().getConfig().getString("inventory-create-title")));
        try {
            ItemStack itemhand = new ItemStack(p.getEquipment().getItemInMainHand().getType(),1);
            ItemStack itempa = new ItemStack(p.getEquipment().getItemInMainHand());
            ItemMeta itempameta = itempa.getItemMeta();
            itemhand.setItemMeta(itempameta);
            String displaynameitem = itemhand.getItemMeta().getDisplayName();
            List<String> loreitem = itemhand.getItemMeta().getLore();
            ItemStack itemnew = itemhand;
            ItemMeta itemmeta = itemnew.getItemMeta();
            itemmeta.setDisplayName(displaynameitem);
            itemmeta.setLore(loreitem);
            itemnew.setItemMeta(itemmeta);
            //back
            ItemStack back = new ItemStack(Material.PRISMARINE_SHARD,1);
            ItemMeta backmeta = back.getItemMeta();
            backmeta.setDisplayName(ChatColor.RED + "BACK");
            List<String> backlore = new ArrayList<>();
            backlore.add("Back");
            backlore.add("To previous menu");
            backmeta.setLore(backlore);
            back.setItemMeta(backmeta);
            //options
            ItemStack options = new ItemStack(Material.NETHER_STAR,1);
            ItemMeta optionsmeta = options.getItemMeta();
            optionsmeta.setDisplayName(ChatColor.AQUA + "OPTIONS");
            List<String> optionslore = new ArrayList<>();
            optionslore.add(ChatColor.GRAY + "You can put");
            optionslore.add(ChatColor.GRAY + "Things that can");
            optionsmeta.setLore(optionslore);
            options.setItemMeta(optionsmeta);



            create.setItem(0,itemhand);
            create.setItem(7,options);
            create.setItem(8,back);
            p.openInventory(create);
        }catch (NullPointerException exception){
            p.sendMessage("You don´t use air for this");
        }

    }
    public static void EditInventory(Player p ){


        try {
            Inventory edit = Bukkit.createInventory(null,
                    54,
                    ChatColor.translateAlternateColorCodes('&', HermitMain.getPlugin().getConfig().getString("inventory-edit-title")));
            //playeritemhand
            ItemStack playeriteminhand = p.getEquipment().getItemInMainHand();
            ItemMeta playeritemmeta = playeriteminhand.getItemMeta();
            String displayitem = playeritemmeta.getDisplayName();
            List<String> loreitem = playeritemmeta.getLore();
            ItemStack itemmain = new ItemStack(playeriteminhand.getType(), 1);
            ItemMeta itemainmeta = itemmain.getItemMeta();
            ItemStack pepa = new ItemStack(p.getEquipment().getItemInMainHand().getType(),1);
            ItemMeta pepaameta = playeriteminhand.getItemMeta();
            pepa.setItemMeta(pepaameta);


            Map<Enchantment, Integer> enchantments = itemmain.getEnchantments();
            Enchantment enchantment;

      //      for (Map.Entry<Enchantment, Integer> mapEntry : itemmain.getEnchantments().entrySet()) {
          //      Enchantment enchant = mapEntry.getKey();
          //      int level = mapEntry.getValue();
               //itemmain.addEnchantment(enchant,level);
                //itemainmeta.addEnchant(enchant,level,true);
                //itemainmeta.addEnchant(enchant,level,true);

                itemainmeta.setDisplayName(displayitem);
                itemainmeta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);

                //
                itemainmeta.setLore(loreitem);
                itemmain.setItemMeta(itemainmeta);

                p.sendMessage(ChatColor.GRAY + "XD");
                //
                ItemStack test = new ItemStack(Material.BARRIER, 1);
                ItemMeta testmeta = test.getItemMeta();
                testmeta.setDisplayName(ChatColor.AQUA + "Edit Inventory");
                List<String> loretest = new ArrayList<>();
                loretest.add(ChatColor.GRAY + "remove all");
                loretest.add(ChatColor.GRAY + "skills");
                testmeta.setLore(loretest);
                test.setItemMeta(testmeta);

                ItemStack backitem = new ItemStack(Material.PRISMARINE_SHARD, 1);
                ItemMeta backmeta = backitem.getItemMeta();
                backmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "BACK");
                List<String> backlore = new ArrayList<>();
                backlore.add(ChatColor.GRAY + "returns previus page");
                backmeta.setLore(backlore);
                backitem.setItemMeta(backmeta);


                edit.setItem(0, itemmain);
                edit.setItem(53, backitem);
                edit.setItem(1,pepa);

                p.openInventory(edit);





           // }
        }catch (NullPointerException exception){

            }}

    public static void OptionInventory(Player p ){

        Inventory options = Bukkit.createInventory(null,54,ChatColor.translateAlternateColorCodes('&',HermitMain.getPlugin().getConfig().getString("Inventory-options-title")));
        //BACK
        ItemStack optiontsitem = new ItemStack(Material.PRISMARINE_SHARD,1);
        ItemMeta optionsmeta  = optiontsitem.getItemMeta();
        optionsmeta.setDisplayName(ChatColor.DARK_AQUA + "BACK");
        List<String> loreoptions = new ArrayList<>();
        loreoptions.add(ChatColor.GRAY + "BACK TO PREVIUS PAGE");
        optionsmeta.setLore(loreoptions);
        optiontsitem.setItemMeta(optionsmeta);
        //ITEMAIN
        ItemStack iteminhand = new ItemStack(p.getEquipment().getItemInMainHand().getType(),1);
        ItemStack itemmeta = p.getEquipment().getItemInMainHand();
        ItemMeta itemmetafinal = itemmeta.getItemMeta();
        iteminhand.setItemMeta(itemmetafinal);
        //OPTION1
        ItemStack optionslight = new ItemStack(Material.WHITE_DYE,1);
        ItemMeta meta_light = optionslight.getItemMeta();
        meta_light.setDisplayName(ChatColor.GOLD + "Item of Storm");
        List<String> lore_light = new ArrayList<>();
        lore_light.add(ChatColor.GRAY + "Sets that your item");
        lore_light.add(ChatColor.GRAY + "can launch lightning");
        lore_light.add(ChatColor.GRAY + "" + ChatColor.BOLD + "with the right click");
        lore_light.add(ChatColor.GRAY + "every so often");
        meta_light.setLore(lore_light);
        optionslight.setItemMeta(meta_light);

        options.setItem(31,iteminhand);
        options.setItem(53,optiontsitem);
        options.setItem(19,optionslight);
        p.openInventory(options);
        //Falta por hacer cuando clickee el item se ponga con tormenta click derecho



    }
}
