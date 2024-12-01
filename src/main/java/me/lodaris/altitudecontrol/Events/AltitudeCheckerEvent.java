package me.lodaris.altitudecontrol.Events;

import me.lodaris.altitudecontrol.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class AltitudeCheckerEvent {
    private final Main plugin;
    private BukkitTask currentTask;

    public AltitudeCheckerEvent(Main plugin) {
        this.plugin = plugin;
    }

    public void startChecking() {
        restartChecking(); // Инициализируем задачу при старте
    }

    public void restartChecking() {

        if (currentTask != null && !currentTask.isCancelled()) {
            currentTask.cancel();
        }

        long damagePeriod = plugin.getConfig().getLong("damagePeriod", 10L);
        double maxHeight = plugin.getConfig().getDouble("maxHeight");
        double minHeight = plugin.getConfig().getDouble("minHeight");
        double damage = plugin.getConfig().getDouble("damage");

        currentTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().getY() > minHeight && player.getLocation().getY() < maxHeight) {
                    player.damage(damage);
                }
            }
        }, 0L, damagePeriod);
    }
}
