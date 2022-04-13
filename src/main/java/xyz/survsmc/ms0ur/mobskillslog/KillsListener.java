package xyz.survsmc.ms0ur.mobskillslog;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;


public class KillsListener implements Listener {
    @EventHandler
    public void kill(@NotNull EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            Entity vistim = event.getEntity();
            Location coords = event.getEntity().getLocation();
            double coordsx = coords.getX();
            double coordsy = coords.getY();
            double coordsz = coords.getZ();
            Sql.sqliteNewKill(Date(), killer.getName(), vistim.getName(), coordsx, coordsy, coordsz);
        }

    }
    private String Date(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd '; time:' hh:mm:ss a zzz");
        return ("Date: " + formatForDateNow.format(dateNow));
    }


}