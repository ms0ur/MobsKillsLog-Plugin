package xyz.survsmc.ms0ur.mobskillslog;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.survsmc.ms0ur.mobskillslog.MobsKillsLog.ConfigSettings;

public class Config {
    static JavaPlugin plugin = MobsKillsLog.getJavaPlugin();
    static FileConfiguration config = plugin.getConfig();
    public static void cfgCreate(){


        if(!config.isBoolean("enable"))//enabled?
            config.set("enable", true);
        if(!config.isString("no_console"))
            config.set("no_console", "It is not possible to use this from the console!");
    }

    public static void cfgInit(){
        ConfigSettings.Settings.enabled = config.getBoolean("enable");
        ConfigSettings.LangStr.no_console=config.getString("no_console");
        plugin.saveConfig();
    }
}
