package me.lodaris.altitudecontrol;

import me.lodaris.altitudecontrol.commands.AltitudeControlCommand;
import me.lodaris.altitudecontrol.Events.AltitudeCheckerEvent;
import me.lodaris.altitudecontrol.commands.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    private AltitudeCheckerEvent altitudeCheckerEvent;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        getCommand("altitudecontrol").setExecutor(new AltitudeControlCommand());
        getCommand("altitudecontrol").setTabCompleter(new TabCompleter());

        altitudeCheckerEvent = new AltitudeCheckerEvent(this);
        altitudeCheckerEvent.startChecking();
    }

    @Override
    public void onDisable() {

        saveConfig();

        if (altitudeCheckerEvent != null) {
            altitudeCheckerEvent.restartChecking();
        }
    }

    public static Main getInstance() { return instance; }

    public AltitudeCheckerEvent getAltitudeCheckerEvent() {
        return altitudeCheckerEvent;
    }
}
