package me.callahandevelopment.hermit.tasks;

import me.callahandevelopment.hermit.HermitMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;


public class ItemTask  {
    int timer;
    int TASKID;
    private HermitMain plugin;
    public ItemTask(HermitMain plugin){
        this.plugin = plugin;
    }



    public void onTask( final String player,long ticks) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        timer = 1;
        TASKID = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (timer==0){
                    plugin.removePlayer(player);
                    Bukkit.getScheduler().cancelTask(TASKID);
                }else {
                    timer--;

                }
            }
        },0L,1L*ticks);

    }


}
