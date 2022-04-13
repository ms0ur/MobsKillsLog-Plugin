package xyz.survsmc.ms0ur.mobskillslog;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class MobsKillsLog extends JavaPlugin {


    public static class ConfigSettings{
        public static class LangStr{
            public static String no_console;
        }
        public static class Settings{
            public static boolean enabled;
        }
    }


    private static MobsKillsLog plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        this.getLogger().info("Starting...");
        Config.cfgCreate();
        Config.cfgInit();
        this.getLogger().info("Config is initialized, checking setting enabled...");
        if(!ConfigSettings.Settings.enabled){
            this.getLogger().warning("enabled = false, plugin can't loading");
            plugin.getServer().getPluginManager().disablePlugin(this);
        }
        this.getLogger().info("Init SQLite...");
        Sql.sqliteCreate();
        this.getLogger().info("init command&events...");
        Objects.requireNonNull(getCommand("mobskills")).setExecutor(new MobsKillsCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new KillsListener(), this);
        this.getLogger().info("Successful.");

    }

    public void onReload() {
        Config.cfgInit();
        if(!ConfigSettings.Settings.enabled){
            plugin.getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MobsKillsLog getJavaPlugin() {
        return plugin;
    }
}
