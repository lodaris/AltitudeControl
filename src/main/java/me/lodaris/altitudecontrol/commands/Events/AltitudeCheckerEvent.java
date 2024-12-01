package me.lodaris.altitudecontrol.commands.Events;

import me.lodaris.altitudecontrol.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AltitudeCheckerEvent {

    private final Main plugin;

    public AltitudeCheckerEvent(Main plugin) {
        this.plugin = plugin;
    }

    public void startChecking() {
        new BukkitRunnable() {
            @Override
            public void run() {

                double maxHeight = Main.getInstance().getConfig().getDouble("maxHeight");
                double damage = Main.getInstance().getConfig().getDouble("damage");

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getLocation().getY() > maxHeight) {
                        player.damage(damage);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 10L);
    }
}
