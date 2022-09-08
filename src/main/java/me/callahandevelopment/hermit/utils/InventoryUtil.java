package me.callahandevelopment.hermit.utils;


import me.callahandevelopment.hermit.HermitMain;
import me.callahandevelopment.hermit.files.LangYaml;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.messaging.ChannelNameTooLongException;

import java.awt.*;
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
        Inventory main = Bukkit.createInventory(null,9,ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Inventory-main-title")));
        //CREATE
        ItemStack create = new ItemStack(Material.GREEN_WOOL,1);
        ItemMeta createmeta = create.getItemMeta();
        createmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("inventory-main-title-item")));
        List<String> lorecreate = LangYaml.get().getStringList("inventory-main-title-item-lore");
        List<String> lorecreate1 = new ArrayList<>();
        for (String text  : lorecreate){
            lorecreate1.add(ChatColor.translateAlternateColorCodes('&',text));
        }
        createmeta.setLore(lorecreate1);
        create.setItemMeta(createmeta);
        //EDIT
        ItemStack edit = new ItemStack(Material.NAME_TAG,1);
        ItemMeta editmeta = edit.getItemMeta();
        editmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("inventory-main-title-edit")));
        List<String> lore = LangYaml.get().getStringList("inventory-main-title-edit-lore");
        List<String> lore1 = new ArrayList<>();
        for (String text  : lore){
            lore1.add(ChatColor.translateAlternateColorCodes('&',text));
        }
        editmeta.setLore(lore1);
        edit.setItemMeta(editmeta);

        //LEAVE
        ItemStack leave = new ItemStack(Material.RED_WOOL,1);
        ItemMeta leavemeta = leave.getItemMeta();
        leavemeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Inventory-leave-item-name")));
        List<String> loreleave = LangYaml.get().getStringList("Inventory-leave-item-lore");
        List<String> loreleave1 = new ArrayList<>();
        for (String text  : loreleave){
            loreleave1.add(ChatColor.translateAlternateColorCodes('&',text));
        }
        leavemeta.setLore(loreleave1);
        leave.setItemMeta(leavemeta);
        main.setItem(0,create);
        main.setItem(1,edit);
        main.setItem(8,leave);
        p.openInventory(main);




    }
    public static void CreateInventory(Player p){

        Inventory create = Bukkit.createInventory(null,9,ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("inventory-create-title")));
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
            backmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Options-back-item-name")));
            List<String> backlore = LangYaml.get().getStringList("Options-back-item-lore");
            List<String> backlore1 = new ArrayList<>();
            for (String text : backlore) {
                backlore1.add(ChatColor.translateAlternateColorCodes('&', text));
            }
            backmeta.setLore(backlore1);
            back.setItemMeta(backmeta);
            //options
            ItemStack options = new ItemStack(Material.NETHER_STAR,1);
            ItemMeta optionsmeta = options.getItemMeta();
            optionsmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-netherstar-name")));
            List<String> optionslore = LangYaml.get().getStringList("Options-netherstar-lore");
            List<String> optionslore1 = new ArrayList<>();
            for (String text  : optionslore){
                optionslore1.add(ChatColor.translateAlternateColorCodes('&',text));
            }
            optionsmeta.setLore(optionslore1);
            options.setItemMeta(optionsmeta);



            create.setItem(0,itemhand);
            create.setItem(7,options);
            create.setItem(8,back);
            p.openInventory(create);
        }catch (NullPointerException exception){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("warning-messsage-air-click")));
        }

    }
    public static void EditInventory(Player p ){


        try {
            Inventory edit = Bukkit.createInventory(null,
                    54,
                    ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("inventory-edit-title")));

            //ITEMAIN
            ItemStack itemmain = new ItemStack(p.getEquipment().getItemInMainHand().getType(),1);
            ItemStack itemStack = p.getEquipment().getItemInMainHand();
            ItemMeta itemstackmeta = itemStack.getItemMeta();
            PersistentDataContainer data = itemstackmeta.getPersistentDataContainer();
            List<String> lorewithskill = new ArrayList<>();

            if(data.has(new NamespacedKey(HermitMain.getPlugin(),"message"),PersistentDataType.STRING)==true){


                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Lightning");



            }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"skull"),PersistentDataType.STRING)){

                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Skull");



            }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"dye"),PersistentDataType.STRING)){
                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Dye");


            }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"fireball"),PersistentDataType.STRING)){
                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Fireball");

            }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"arrow"),PersistentDataType.STRING)){
                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Arrow");


            }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"tnt"),PersistentDataType.STRING)){
                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill TNT");



            } if (data.has(new NamespacedKey(HermitMain.getPlugin(),"potion"),PersistentDataType.STRING)){
                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Potion");


            } if (data.has(new NamespacedKey(HermitMain.getPlugin(),"firework"),PersistentDataType.STRING)){
                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Firework");


            } if (data.has(new NamespacedKey(HermitMain.getPlugin(),"trident"),PersistentDataType.STRING)){
                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Trident");



            } if (data.has(new NamespacedKey(HermitMain.getPlugin(),"snow"),PersistentDataType.STRING)) {
                lorewithskill.add(ChatColor.LIGHT_PURPLE + "Skill Snow");





            }

            else {
                ItemStack mainhanditem = new ItemStack(p.getEquipment().getItemInMainHand().getType(),1);
                ItemStack itemstack = p.getEquipment().getItemInMainHand();
                ItemMeta mainhanditemmeta = itemstack.getItemMeta();
                mainhanditem.setItemMeta(mainhanditemmeta);
                edit.setItem(22,mainhanditem);
            }
            itemstackmeta.setLore(lorewithskill);
            itemmain.setItemMeta(itemstackmeta);
            edit.setItem(22,itemmain);


                //BACK
                ItemStack backitem = new ItemStack(Material.PRISMARINE_SHARD, 1);
                ItemMeta backmeta = backitem.getItemMeta();
                backmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Options-back-item-name")));
                List<String> backlore = LangYaml.get().getStringList("Options-back-item-lore");
                List<String> backlore1 = new ArrayList<>();
                for (String text : backlore) {
                    backlore1.add(ChatColor.translateAlternateColorCodes('&', text));
                }
                backmeta.setLore(backlore1);
                backitem.setItemMeta(backmeta);
                //decorate
            ItemStack decorate = new ItemStack(Material.BLACK_STAINED_GLASS,1);
            decorate.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,1);
            ItemMeta meta_decorate = decorate.getItemMeta();
            meta_decorate.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta_decorate.setDisplayName(" ");
            decorate.setItemMeta(meta_decorate);
                //GLASS
            //OPTIONSFINAL
            ItemStack paper = new ItemStack(Material.PAPER,1);
            ItemMeta bookmeta = paper.getItemMeta();
            bookmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Edit-paper-name")));
            List<String> booklore = LangYaml.get().getStringList("Edit-paper-lore");
            List<String> booklore1 = new ArrayList<>();
            for (String text : booklore){
                booklore1.add(ChatColor.translateAlternateColorCodes('&',text));
            }
            bookmeta.setLore(booklore1);
            paper.setItemMeta(bookmeta);
            //OPTIONS
            ItemStack barrier = new ItemStack(Material.BARRIER,1);
            ItemMeta barriermeta = barrier.getItemMeta();
            barriermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Edit-barrier-name")));
            List<String> barrierlore = LangYaml.get().getStringList("Edit-barrier-lore");
            List<String> barrierlore1 = new ArrayList<>();
            for (String text : barrierlore){
                barrierlore1.add(ChatColor.translateAlternateColorCodes('&',text));
            }
            barriermeta.setLore(barrierlore1);
            barrier.setItemMeta(barriermeta);
            //OPTIONS



           for (int i = 36; i < edit.getSize(); i++) {
                edit.setItem(i, new ItemStack(decorate));}
            for (int i =0;i<edit.getSize()-45;i++){
                edit.setItem(i,new ItemStack(decorate));
            }
            for (int i =8;i<edit.getSize()-32;i++) {
                edit.setItem(i, new ItemStack(decorate));
            }
            for (int i=23;i<edit.getSize();i++){
                edit.setItem(i,new ItemStack(decorate));
            }



                edit.setItem(44, backitem);
                edit.setItem(16,paper);
                edit.setItem(10,barrier);

                ;

                p.openInventory(edit);





           // }
        }catch (NullPointerException exception){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("warning-messsage-air-click")));

            }}


    public static void OptionInventory(Player p ){


        Inventory options = Bukkit.createInventory(null,54,ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Inventory-options-title")));
        //BACK
        ItemStack optiontsitem = new ItemStack(Material.PRISMARINE_SHARD,1);
        ItemMeta optionsmeta  = optiontsitem.getItemMeta();
        optionsmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', LangYaml.get().getString("Options-back-item-name")));
        List<String> backitem = LangYaml.get().getStringList("Options-back-item-lore");
        List<String> c = new ArrayList<>();
        for (String text : backitem) {
            c.add(ChatColor.translateAlternateColorCodes('&', text));
        }
        optionsmeta.setLore(c);
        optiontsitem.setItemMeta(optionsmeta);

        //ITEMAIN
        ItemStack itemmain = new ItemStack(p.getEquipment().getItemInMainHand().getType(),1);
        ItemStack itemStack = p.getEquipment().getItemInMainHand();
        ItemMeta itemstackmeta = itemStack.getItemMeta();
        PersistentDataContainer data = itemstackmeta.getPersistentDataContainer();
        List<String> lorewithskill = new ArrayList<>();

         if(data.has(new NamespacedKey(HermitMain.getPlugin(),"message"),PersistentDataType.STRING)==true){


             lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-lightning")));



         }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"skull"),PersistentDataType.STRING)){

            lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-skull")));



        }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"dye"),PersistentDataType.STRING)){
            lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-Dragon")));



        }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"fireball"),PersistentDataType.STRING)){
               lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-fireball")));


        }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"arrow"),PersistentDataType.STRING)){
            lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-Arrow")));



        }if (data.has(new NamespacedKey(HermitMain.getPlugin(),"tnt"),PersistentDataType.STRING)){
            lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-TNT")));



        } if (data.has(new NamespacedKey(HermitMain.getPlugin(),"potion"),PersistentDataType.STRING)){
            lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-potion")));


        } if (data.has(new NamespacedKey(HermitMain.getPlugin(),"firework"),PersistentDataType.STRING)){
             lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-firework")));


        } if (data.has(new NamespacedKey(HermitMain.getPlugin(),"trident"),PersistentDataType.STRING)){
            lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-Trident")));



        } if (data.has(new NamespacedKey(HermitMain.getPlugin(),"snow"),PersistentDataType.STRING)) {
            lorewithskill.add(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-lore-show-skill-Snowball")));





        }

         else {
             ItemStack mainhanditem = new ItemStack(p.getEquipment().getItemInMainHand().getType(),1);
             ItemStack itemstack = p.getEquipment().getItemInMainHand();
             ItemMeta mainhanditemmeta = itemstack.getItemMeta();
             mainhanditem.setItemMeta(mainhanditemmeta);
             options.setItem(22,mainhanditem);
         }
        itemstackmeta.setLore(lorewithskill);
        itemmain.setItemMeta(itemstackmeta);
         options.setItem(22,itemmain);









        //OPTION1
        ItemStack optionslight = new ItemStack(Material.WHITE_DYE,1);
        ItemMeta meta_light = optionslight.getItemMeta();
       meta_light.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-lightning-name")));
       List<String> lore_light = LangYaml.get().getStringList("Options-item-skill-skull-lore");
        List<String> lore_light1 = new ArrayList<>();
        for (String text : lore_light) {
            lore_light1.add(ChatColor.translateAlternateColorCodes('&', text));
        }
        meta_light.setLore(lore_light1);
        optionslight.setItemMeta(meta_light);
        //OPTION2
        ItemStack optionsfire = new ItemStack(Material.FIRE_CHARGE,1);
        ItemMeta meta_fire = optionsfire.getItemMeta();
        meta_fire.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-fireball-name")));
        List<String> lore_fire = LangYaml.get().getStringList("Options-item-skill-fireball-lore");
        List<String> lore_fire1 = new ArrayList<>();
        for (String text : lore_fire) {
            lore_fire1.add(ChatColor.translateAlternateColorCodes('&', text));
        }

        meta_fire.setLore(lore_fire1);
        optionsfire.setItemMeta(meta_fire);
        //OPTIONS3
        ItemStack optionsskull = new ItemStack(Material.WITHER_SKELETON_SKULL,1);
        ItemMeta meta_skull = optionsskull.getItemMeta();
        meta_skull.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-skull-name")));
        List<String> lore_skull = LangYaml.get().getStringList("Options-item-skill-skull-lore");
        List<String> lore_skull1 = new ArrayList<>();
        for (String text : lore_skull) {
            lore_skull1.add(ChatColor.translateAlternateColorCodes('&', text));
        }
        meta_skull.setLore(lore_skull1);
        optionsskull.setItemMeta(meta_skull);
        //OPTION4
        ItemStack optionsdragon = new ItemStack(Material.BLACK_DYE,1);
        ItemMeta meta_dragon = optionsdragon.getItemMeta();
        meta_dragon.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-dragon-name")));
        List<String> lore_dragon = LangYaml.get().getStringList("Options-item-skill-dragon-lore");
        List<String> lore_dragon1 = new ArrayList<>();
        for (String text : lore_dragon) {
            lore_dragon1.add(ChatColor.translateAlternateColorCodes('&', text));
        }

        meta_dragon.setLore(lore_dragon1);
        optionsdragon.setItemMeta(meta_dragon);
        //OPTION5
        ItemStack options_5 = new ItemStack(Material.ARROW,1);
        ItemMeta meta_5 = options_5.getItemMeta();
        meta_5.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-arrow-name")));
        List<String> lore_5 = LangYaml.get().getStringList("Options-item-skill-arrow-lore");
        List<String> lore_5_1 = new ArrayList<>();
        for (String text : lore_5) {
            lore_5_1.add(ChatColor.translateAlternateColorCodes('&', text));
        }
        meta_5.setLore(lore_5_1);
        options_5.setItemMeta(meta_5);
        //OPTION6
        ItemStack options_6 = new ItemStack(Material.TNT,1);
        ItemMeta meta_6 = options_6.getItemMeta();
        meta_6.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-tnt-name")));
        List<String> lore_6 = LangYaml.get().getStringList("Options-item-skill-tnt-lore");
        List<String> lore_6_1 = new ArrayList<>();
        for (String text : lore_6) {
            lore_6_1.add(ChatColor.translateAlternateColorCodes('&', text));
        }

        meta_6.setLore(lore_6_1);
        options_6.setItemMeta(meta_6);
        //OPTION7
        ItemStack options_7 = new ItemStack(Material.FIREWORK_ROCKET,1);
        ItemMeta meta_7 = options_7.getItemMeta();
        meta_7.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-firework-name")));
        List<String> lore_7 = LangYaml.get().getStringList("Options-item-skill-firework-lore");
        List<String> lore_7_1 = new ArrayList<>();
        for (String text : lore_7) {
            lore_7_1.add(ChatColor.translateAlternateColorCodes('&', text));
        }

        meta_7.setLore(lore_7_1);
        options_7.setItemMeta(meta_7);
        //OPTION8
        ItemStack options_8 = new ItemStack(Material.SPLASH_POTION,1);
        ItemMeta meta_8 = options_8.getItemMeta();
        meta_8.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-potion-name")));
        List<String> lore_8 = LangYaml.get().getStringList("Options-item-skill-potion-lore");
        List<String> lore_8_1 = new ArrayList<>();
        for (String text : lore_8) {
            lore_8_1.add(ChatColor.translateAlternateColorCodes('&', text));
        }
        meta_8.setLore(lore_8_1);
        options_8.setItemMeta(meta_8);
        //OPTION9
        ItemStack options_9 = new ItemStack(Material.TRIDENT,1);
        ItemMeta meta_9 = options_9.getItemMeta();
        meta_9.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-trident-name")));
        List<String> lore_9 = LangYaml.get().getStringList("Options-item-skill-trident-lore");
        List<String> lore_9_1 = new ArrayList<>();
        for (String text : lore_9) {
            lore_9_1.add(ChatColor.translateAlternateColorCodes('&', text));
        }
        meta_9.setLore(lore_9_1);
        options_9.setItemMeta(meta_9);
        //OPTION10
        ItemStack options_10 = new ItemStack(Material.SNOWBALL,1);
        ItemMeta meta_10 = options_10.getItemMeta();
        meta_10.setDisplayName(ChatColor.translateAlternateColorCodes('&',LangYaml.get().getString("Options-item-skill-snow-name")));
        List<String> lore_10 = LangYaml.get().getStringList("Options-item-skill-snow-lore");
        List<String> lore_10_1 = new ArrayList<>();
        for (String text : lore_10) {
            lore_10_1.add(ChatColor.translateAlternateColorCodes('&', text));
        }
        meta_10.setLore(lore_10_1);
        options_10.setItemMeta(meta_10);
        //decoration
        ItemStack decorate = new ItemStack(Material.BLACK_STAINED_GLASS,1);
        decorate.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,1);
        ItemMeta meta_decorate = decorate.getItemMeta();
        meta_decorate.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta_decorate.setDisplayName(" ");
        decorate.setItemMeta(meta_decorate);

    for (int i = 36; i < options.getSize(); i++) {
        options.setItem(i, new ItemStack(decorate));}
    for (int i =0;i<options.getSize()-45;i++){
        options.setItem(i,new ItemStack(decorate));
    }
    for (int i =8;i<options.getSize()-32;i++) {
        options.setItem(i, new ItemStack(decorate));
    }
    for (int i=23;i<options.getSize();i++){
        options.setItem(i,new ItemStack(decorate));
    }

      options.setItem(12,options_6);
      options.setItem(30,options_7);
      options.setItem(20,options_5);
      options.setItem(44,optiontsitem);
      options.setItem(10,optionslight);
      options.setItem(16,optionsfire);
      options.setItem(28,optionsskull);
      options.setItem(34,optionsdragon);
      options.setItem(24,options_8);
      options.setItem(14,options_9);
      options.setItem(32,options_10);


     p.openInventory(options);
        //Falta organizar el inventario



    }
}
