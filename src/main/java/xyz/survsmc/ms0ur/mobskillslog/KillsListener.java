package xyz.survsmc.ms0ur.mobskillslog;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.bukkit.Bukkit.getName;


public class KillsListener implements Listener {
    @EventHandler
    public void kill(@NotNull EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null && !(event.getEntity() instanceof Player)) {
            String killer = event.getEntity().getKiller().getName();
            String vistim = event.getEntity().getType().toString();
            Location coords = event.getEntity().getLocation();
            double coordsx = coords.getX();
            double coordsy = coords.getY();
            double coordsz = coords.getZ();
            bd.newKill(killer, vistim, coordsx, coordsy, coordsz);
        }

    }


}