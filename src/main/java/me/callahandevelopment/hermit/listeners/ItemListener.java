package me.callahandevelopment.hermit.listeners;

import me.callahandevelopment.hermit.HermitMain;
import me.callahandevelopment.hermit.files.LangYaml;
import me.callahandevelopment.hermit.tasks.ItemTask;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.Snow;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.*;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class ItemListener implements Listener {
    private final  HashMap<UUID,Long> cooldown;
    private final HashMap<UUID,Long> cooldown2;
    private final HashMap<UUID,Long> cooldown3;
    private final HashMap<UUID,Long> cooldown4;
    private final HashMap<UUID,Long> cooldown5;
    private final HashMap<UUID,Long> cooldown6;
    private final HashMap<UUID,Long> cooldown7;
    private final HashMap<UUID,Long> cooldown8;
    private final HashMap<UUID,Long> cooldown9;
    private final HashMap<UUID,Long> cooldown10;

    private HermitMain plugin;
    public ItemListener(HermitMain plugin){
        this.plugin = plugin;
        this.cooldown = new HashMap<>();
        this.cooldown2 = new HashMap<>();
        this.cooldown3 = new HashMap<>();
        this.cooldown4 = new HashMap<>();
        this.cooldown5 = new HashMap<>();
        this.cooldown6 = new HashMap<>();
        this.cooldown7 = new HashMap<>();
        this.cooldown8 = new HashMap<>();
        this.cooldown9 = new HashMap<>();
        this.cooldown10 = new HashMap<>();
    }


    @EventHandler
    public void onRightClickItem(PlayerInteractEvent e){


        ItemStack itemhand = e.getItem();
        Player player = e.getPlayer();
      try {
            //
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                if (e.getItem()!=null||e.getItem().getType()!=Material.AIR){
                    if (e.getItem().getItemMeta()!=null){
                ItemMeta itemmeta = itemhand.getItemMeta();
                PersistentDataContainer itemdata = itemmeta.getPersistentDataContainer();
                if (    itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"message"), PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"skull"),PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"dye"),PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"fireball"),PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"arrow"),PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"tnt"),PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"potion"),PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"firework"),PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"trident"),PersistentDataType.STRING)||
                        itemdata.has(new NamespacedKey(HermitMain.getPlugin(),"snow"),PersistentDataType.STRING)
                        ) {
                    int cooldownTime = plugin.getConfig().getInt("Cooldown-Item");
                    int resultado = cooldownTime * 1000;

                    if (!cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) >= resultado) {
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "message"), PersistentDataType.STRING)) {
                            Block block = player.getTargetBlock(null, 50);
                            LightningStrike lightningStrike = player.getWorld().strikeLightning(block.getLocation());
                            lightningStrike.setFireTicks(HermitMain.getPlugin().getConfig().getInt("Lightning-fire-time") * 20);
                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "skull"), PersistentDataType.STRING)) {
                            WitherSkull witherSkull = player.getWorld().spawn(player.getLocation().add(0, 1.1, 0), WitherSkull.class);
                            witherSkull.setVelocity(player.getLocation().getDirection().multiply(2.5));
                            witherSkull.setCharged(HermitMain.getPlugin().getConfig().getBoolean("Skull-effect"));

                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "dye"), PersistentDataType.STRING)) {
                            DragonFireball dragonFireball = player.getWorld().spawn(player.getLocation().add(0, 1.2, 0), DragonFireball.class);
                            dragonFireball.setVelocity(player.getLocation().getDirection().multiply(2.4));

                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "fireball"), PersistentDataType.STRING)) {
                            Fireball fireball = player.getWorld().spawn(player.getLocation().add(0, 1.3, 0), Fireball.class);
                            fireball.setVelocity(player.getLocation().getDirection().multiply(2.3));

                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "arrow"), PersistentDataType.STRING)) {
                            Arrow arrow = player.getWorld().spawn(player.getLocation().add(0, 1.4, 0), Arrow.class);
                            arrow.setVelocity(e.getPlayer().getLocation().getDirection());
                            arrow.setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.2));
                            String arrowtype = HermitMain.getPlugin().getConfig().getString("Type-Arrow");
                            arrow.setBasePotionData(new PotionData(PotionType.valueOf(arrowtype)));
                            arrow.setDamage(HermitMain.getPlugin().getConfig().getInt("Arrow-Damage"));
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.valueOf(HermitMain.getPlugin().getConfig().getString("Pickup-Arrow")));

                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "tnt"), PersistentDataType.STRING)) {
                            TNTPrimed tntPrimed = player.getWorld().spawn(player.getLocation().add(0, 0, 0), TNTPrimed.class);
                            tntPrimed.setFuseTicks(HermitMain.getPlugin().getConfig().getInt("TNT-time-explosion") * 20);
                            tntPrimed.setVelocity(e.getPlayer().getLocation().getDirection());
                            tntPrimed.setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.1));

                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "potion"), PersistentDataType.STRING)) {
                            ItemStack itemStack = new ItemStack(Material.SPLASH_POTION);
                            String potiontype = HermitMain.getPlugin().getConfig().getString("Potion-Type");
                            PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
                            String configpotion = HermitMain.getPlugin().getConfig().getString("Potion-Type");
                            potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.getByName(new String(configpotion)), HermitMain.getPlugin().getConfig().getInt("Potion-Duration"), HermitMain.getPlugin().getConfig().getInt("Potion-Amplifier")), true);

                            itemStack.setItemMeta(potionMeta);

                            ThrownPotion thrownPotion = (ThrownPotion) player.getWorld().spawnEntity(player.getLocation().add(0, 1.5, 0), EntityType.SPLASH_POTION);
                            thrownPotion.setItem(itemStack);
                            //ThrownPotion thrownPotion = player.getWorld().spawn(player.getLocation().add(0,1.5,0), ThrownPotion.class);

                            thrownPotion.setVelocity(e.getPlayer().getLocation().getDirection());
                            thrownPotion.setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.0));
                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "firework"), PersistentDataType.STRING)) {
                            Firework firework = player.getWorld().spawn(player.getLocation().add(0, 1.6, 0), Firework.class);
                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                            FireworkEffect.Builder builder = FireworkEffect.builder();
                            builder.with(FireworkEffect.Type.valueOf(HermitMain.getPlugin().getConfig().getString("Firework-Type")));
                            fireworkMeta.setPower(HermitMain.getPlugin().getConfig().getInt("Firework-Power"));
                            builder.withColor(Color.RED);
                            builder.withFade(Color.BLUE);
                            fireworkMeta.addEffect(builder.build());
                            firework.setFireworkMeta(fireworkMeta);
                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "trident"), PersistentDataType.STRING)) {
                            Trident trident = player.getWorld().spawn(player.getLocation().add(0, 1.7, 0), Trident.class);
                            trident.setVelocity(e.getPlayer().getLocation().getDirection());
                            trident.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.9));
                            trident.setPickupStatus(AbstractArrow.PickupStatus.valueOf(HermitMain.getPlugin().getConfig().getString("Trident-Pickup")));
                            trident.setDamage(HermitMain.getPlugin().getConfig().getInt("Trident-Damage"));
                        }
                        if (itemdata.has(new NamespacedKey(HermitMain.getPlugin(), "snow"), PersistentDataType.STRING)) {
                            Snowball snowball = player.getWorld().spawn(player.getLocation().add(0, 1.8, 0), Snowball.class);
                            snowball.setVelocity(e.getPlayer().getLocation().getDirection());
                            snowball.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.8));
                        }
                        cooldown.put(player.getUniqueId(), System.currentTimeMillis());


                    } else {
                        String message = plugin.getConfig().getString("Cooldown-Message");
                        message = message.replace("%timer%", ((plugin.getConfig().getInt("Cooldown-Item")*1000) - (System.currentTimeMillis() - cooldown.get(player.getUniqueId()))) / 1000 + "");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));


                    }


                } }} else {

                }

            }
        }catch (NullPointerException exception){



        }

    }






}
