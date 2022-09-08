package me.callahandevelopment.hermit.files;

import me.callahandevelopment.hermit.HermitMain;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.awt.print.Paper;
import java.io.File;
import java.util.logging.Logger;

public class LangYaml {
    private static File file;
    private static FileConfiguration yamlLang;

    public static void setUp(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Hermit").getDataFolder(),"lang.yml");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {

            }
        }
        yamlLang = YamlConfiguration.loadConfiguration(file);

    }
    public static FileConfiguration get(){
        return yamlLang;
    }
    public static void save(){
        try {
            yamlLang.save(file);
        } catch (Exception e) {
            System.out.println("Couldn't save file");

        }

    }
    public static void reload(){
        yamlLang = YamlConfiguration.loadConfiguration(file);
    }
}
