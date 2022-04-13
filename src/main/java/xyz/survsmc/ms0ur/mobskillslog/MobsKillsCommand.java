package xyz.survsmc.ms0ur.mobskillslog;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class MobsKillsCommand implements CommandExecutor{


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage(MobsKillsLog.ConfigSettings.LangStr.no_console); return true;
        }
        if(args.length == 0){
            int id = 0;
            int lastid = 10;
            while(!(id == lastid)){
                sender.sendMessage(Outputer.getStringResult(id));
                id++;
            }
        }
        return true;
    }
}
