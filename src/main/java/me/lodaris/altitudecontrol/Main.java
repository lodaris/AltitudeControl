package me.lodaris.altitudecontrol;

import me.lodaris.altitudecontrol.commands.AltitudeControlCommand;
import me.lodaris.altitudecontrol.commands.Events.AltitudeCheckerEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static String getInstance;
    private static Main instance;



    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        this.getCommand("altitudecontrol").setExecutor(new AltitudeControlCommand());

        new AltitudeCheckerEvent(this).startChecking();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() { return instance; }

}
