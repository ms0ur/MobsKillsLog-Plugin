package xyz.survsmc.ms0ur.mobskillslog;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MobsKillsCommand implements CommandExecutor{


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String lbl, @NotNull String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(MobsKillsLog.ConfigSettings.LangStr.no_console);
            return true;
        }
        Location loc = ((Player)sender).getLocation();
        if (args.length == 0) {
            sender.sendMessage(
                    ChatColor.GRAY + "-------------" + ChatColor.RED + "MobsKills" + ChatColor.WHITE + "Log" + ChatColor.GRAY + "-------------",
                    ChatColor.RED + "" + ChatColor.ITALIC + "/" + lbl + " last " + ChatColor.RESET + ChatColor.WHITE + "- Views last 10 kills.",
                    ChatColor.BLUE + "" + ChatColor.ITALIC + "/" + lbl + " searchC [radius] [killer/vistim] [nick/entity type] " + ChatColor.RESET + ChatColor.WHITE + "- Searches killer/vistim kills/dies around radius you.",
                    ChatColor.GRAY + "----------------------------------"
            );
            return true;
        } else if (args[0].equalsIgnoreCase("last")) {
            ArrayList<String> list = bd.getLastTenKills();
            if (list == null) {
                sender.sendMessage(ChatColor.DARK_RED + "Error: return null");
                return true;
            }
            sender.sendMessage(ChatColor.AQUA +"Last 10 mob kills:");
            for (String s : list) {
                sender.sendMessage(s);
            }
            return true;
        } else if (args[0].equalsIgnoreCase("scanner")){
            ArrayList<String> list = bd.search(loc.getX(), loc.getY(), loc.getZ(), 10);
            if (list == null){
                sender.sendMessage(ChatColor.DARK_RED +"Error: return null");
                return true;
            }
        }


        return true;
    }
}