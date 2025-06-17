package org.Rofu.mythicEventMenager;

import org.bukkit.configuration.file.FileConfiguration;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private MythicEventMenager plugin;
    private FileConfiguration config;
    private int delayTimer;
    private int timer;
    private String name;
    private List<Event> events = new ArrayList<>();

    public ConfigManager(MythicEventMenager plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        delayTimer = config.getInt("delay-timer", 60);
        timer = config.getInt("timer", 45);

        // Загрузка ивентов из конфига
        config.getConfigurationSection("events").getKeys(false).forEach(key -> {
            String startCommand = config.getString("events." + key + ".start-command");
            int chance = config.getInt("events." + key + ".chance");
            events.add(new Event(key, startCommand, chance));
        });
    }

    public int getDelayTimer() {
        return delayTimer;
    }

    public int getTimer() {
        return timer;
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getName() {
        return name;
    }
}
